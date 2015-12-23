package controllers;


import jgrid.JGridRowsResponse;
import jgrid.JSComboExpenseResp;
import jgrid.Utils;
import model.Brand;
import model.DeviceType;
import model.Model;
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
import repository.BrandRepository;
import repository.DeviceRepository;
import repository.DeviceTypeRepository;
import repository.ModelRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/models")
public class ModelController {

    @Autowired
    ModelRepository modelRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Secured("ROLE_USER")

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(){

        return "models_list";
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/listing", method = RequestMethod.POST)
    @ResponseBody
    public JGridRowsResponse<model.Model> getTable(HttpServletRequest request){
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
        String filterName=request.getParameter("modelName");
        if(pageRequest!=null){
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(modelRepository.findByModelNameContains(filterName, pageRequest));
            } else
                return new JGridRowsResponse<>(modelRepository.findAll(pageRequest));
        } else {
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(modelRepository.findByModelNameContains(filterName));
            } else
                return new JGridRowsResponse<>(modelRepository.findAll());
        }
    }


    @Transactional(readOnly = false)
    @RequestMapping(value = "/edit", method = {RequestMethod.POST,RequestMethod.GET})
    public void editor(@RequestParam String oper,
                       @RequestParam(required = false) Long id,
                       @RequestParam(required = false)String  modelName,HttpServletRequest request,
                       HttpServletResponse response)throws IOException {

        switch (oper){
            case "add": {
                Model model = new Model();

                model.setModelName(modelName);

                Long price = Utils.getLongParameter("price", request);
                model.setPrice(price);

                Long brandId = Utils.getLongParameter("brend_id", request);
                if (brandId != null) {
                    Brand brand = brandRepository.findOne(brandId);
                    if (brand != null) {
                        model.setBrand(brand);
                    } else {
                        response.sendError(404, "BRAND NOT FOUND!");
                    }
                } else {
                    response.sendError(404, "BRAND NOT FOUND!");
                }

                Long deviceTypeId = Utils.getLongParameter("devicetype_id", request);
                if (deviceTypeId != null) {
                    DeviceType deviceType = deviceTypeRepository.findOne(deviceTypeId);
                    if (deviceType != null) {
                        model.setDeviceType(deviceType);
                    } else {
                        response.sendError(404, "DEVICE TYPE NOT FOUND!");
                    }
                } else {
                    response.sendError(404, "DEVICE TYPE NOT FOUND!");
                }

                modelRepository.save(model);
                response.setStatus(200);
            }break;

            case "edit":
               Model fromDB= modelRepository.getOne(id);
                Long version= Utils.getLongParameter("version",request);

                if(fromDB!=null) {
                    if(version!=null && version>=fromDB.getVersion()) {

                        Long price= Utils.getLongParameter("price",request);
                        fromDB.setPrice(price);

                        Long brandId= Utils.getLongParameter("brand_id",request);
                        if(brandId!=null){
                            Brand brand=brandRepository.findOne(brandId);
                            if(brand!=null){
                                fromDB.setBrand(brand);
                            } else {
                                response.sendError(404,"BRAND NOT FOUND!");
                            }
                        }else {
                            response.sendError(404,"BRAND NOT FOUND!");
                        }

                        Long deviceTypeId = Utils.getLongParameter("devicetype_id", request);
                        if (deviceTypeId != null) {
                            DeviceType deviceType = deviceTypeRepository.findOne(deviceTypeId);
                            if (deviceType != null) {
                                fromDB.setDeviceType(deviceType);
                            } else {
                                response.sendError(404, "DEVICE TYPE NOT FOUND!");
                            }
                        } else {
                            response.sendError(404, "DEVICE TYPE NOT FOUND!");
                        }

                        modelRepository.save(fromDB);
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
                    modelRepository.delete(id);
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
    public Object simpleClientList(@RequestParam(required = false) Integer page_num,
                                   @RequestParam(required = false) Integer per_page,
                                   @RequestParam(value = "pkey_val[]",required = false) String pkey,
                                   @RequestParam(value = "q_word[]",required = false) String[] qword) {
        // Retrieve all persons by delegating the call to PersonService
        //Sort sort= FormSort.formSortFromSortDescription(orderby);
        Sort sort=new Sort(Sort.Direction.ASC,"modelName");
        PageRequest pager=null;
        if(page_num!=null && per_page!=null) {
            pager = new PageRequest(page_num - 1, per_page, sort);
        }
        if(pager!=null) {
            Page<Model> page;
            if (qword != null && qword.length > 0) {
                page = modelRepository.findByModelNameContains(qword[0], pager);
            } else {
                page = modelRepository.findAll(pager);
            }
            return new JSComboExpenseResp<>(page);
        } else {
            if(pkey!=null && !pkey.isEmpty()){
                Long key=Long.valueOf(pkey);
                Model ft=null;
                if(key!=null) {
                    ft = modelRepository.findOne(key);
                }
                return ft;
            } else {
                List<Model> page;
                if (qword != null && qword.length > 0) {
                    page = modelRepository.findByModelNameContains(qword[0], sort);
                } else {
                    page = modelRepository.findAll(sort);
                }
                return new JSComboExpenseResp<>(page);
            }
        }
    }
}