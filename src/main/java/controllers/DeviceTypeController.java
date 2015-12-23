package controllers;

import jgrid.JGridRowsResponse;
import jgrid.JSComboExpenseResp;
import model.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.DeviceTypeRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/devicetypes")
public class DeviceTypeController {


    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Secured("ROLE_USER")

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(){
        return "devicetypes_list";
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/listing", method = RequestMethod.POST)
    @ResponseBody
    public JGridRowsResponse<DeviceType> getTable(HttpServletRequest request){
        PageRequest pageRequest=null;
        if(request.getParameter("page")!=null){
            int rows=10;
            int page;
            try {
                page = Integer.parseInt(request.getParameter("page")) - 1;
                rows = request.getParameter("rows") == null ? 10 : Integer.parseInt(request.getParameter("rows"));
                if(request.getParameter("sidx")!=null && !request.getParameter("sidx").isEmpty()){
                    String direction=request.getParameter("sord");
                    pageRequest=new PageRequest(page,rows,"asc".equals(direction)? Sort.Direction.ASC: Sort.Direction.DESC,request.getParameter("sidx"));
                } else {
                    pageRequest=new PageRequest(page,rows);
                }
            }catch (NumberFormatException ex){
                //do nothing
            }

        }/**/
        String filterName=request.getParameter("deviceTypeName");
        if(pageRequest!=null){
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(deviceTypeRepository.findByDeviceTypeNameContains(filterName, pageRequest));
            } else
                return new JGridRowsResponse<>(deviceTypeRepository.findAll(pageRequest));
        } else {
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(deviceTypeRepository.findByDeviceTypeNameContains(filterName));
            } else
                return new JGridRowsResponse<>(deviceTypeRepository.findAll());
        }
    }


    @Transactional(readOnly = false)
    @RequestMapping(value = "/edit", method = {RequestMethod.POST,RequestMethod.GET})
    public void editor(@RequestParam String oper,@RequestParam(required = false) Long id, DeviceType deviceType,BindingResult result,HttpServletResponse response)throws IOException {
        if(result.hasErrors()){
            response.sendError(400,result.toString());
            return;
        }
        switch (oper){
            case "add":
                deviceTypeRepository.save(deviceType);
                response.setStatus(200);
                break;
            case "edit":
                DeviceType fromDB= deviceTypeRepository.getOne(deviceType.getDeviceTypeId());
                if(fromDB!=null) {
                    if(deviceType.getVersion()>=fromDB.getVersion()) {
                        deviceTypeRepository.save(deviceType);
                        response.setStatus(200);
                    } else {
                        response.sendError(406,"ANOTHER TRANSACTION MODIFICATION");
                    }
                } else {
                    response.sendError(406,"NO CONTACT TYPE FOUND");
                }

                break;
            case "del":
                if(id!=null) {
                    deviceTypeRepository.delete(id);
                    response.setStatus(200);
                }
                break;
            default:
                response.sendError(406,"UNKNOWN OPERATION");
        }
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    @ResponseBody
    public Object simpleClientList(@RequestParam(required = false) Integer page_num, @RequestParam(required = false) Integer per_page,@RequestParam(value = "pkey_val[]",required = false) String pkey,@RequestParam(value = "q_word[]",required = false) String[] qword) {
        //Sort sort= FormSort.formSortFromSortDescription(orderby);
        Sort sort=new Sort(Sort.Direction.ASC,"deviceTypeName");
        PageRequest pager=null;
        if(page_num!=null && per_page!=null) {
            page_num= page_num<1?1:page_num;
            pager = new PageRequest(page_num - 1, per_page, sort);
        }
        if(pager!=null) {
            Page<DeviceType> page;
            if (qword != null && qword.length > 0) {
                page = deviceTypeRepository.findByDeviceTypeNameContains(qword[0], pager);
            } else {
                page = deviceTypeRepository.findAll(pager);
            }
            return new JSComboExpenseResp<>(page);
        } else {
            if(pkey!=null && !pkey.isEmpty()){
                Long key=Long.valueOf(pkey);
                DeviceType ft=null;
                if(key!=null) {
                    ft = deviceTypeRepository.findOne(key);
                }
                return ft;
            } else {
                List<DeviceType> page;
                if (qword != null && qword.length > 0) {
                    page = deviceTypeRepository.findByDeviceTypeNameContains("%" + qword[0] + "%", sort);
                } else {
                    page = deviceTypeRepository.findAll(sort);
                }
                return new JSComboExpenseResp<>(page);
            }
        }
    }
}