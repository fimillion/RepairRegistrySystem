package controllers;

import jgrid.JGridRowsResponse;
import model.Completeness;
import org.springframework.beans.factory.annotation.Autowired;
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
import repository.CompletenessRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/completeness")
public class CompletenessController {

    @Autowired
    CompletenessRepository completenessRepository;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(){
        return "completeness_list";
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/listing", method = RequestMethod.POST)
    @ResponseBody
    public JGridRowsResponse<Completeness> getTable(HttpServletRequest request) {
        PageRequest pageRequest = null;
        if (request.getParameter("page") != null) {
            int rows = 10;
            int page;
            try {
                page = Integer.parseInt(request.getParameter("page")) - 1;
                rows = request.getParameter("rows") == null ? 10 : Integer.parseInt(request.getParameter("rows"));
                if (request.getParameter("sidx") != null && !request.getParameter("sidx").isEmpty()) {
                    String direction = request.getParameter("sord");
                    pageRequest = new PageRequest(page, rows, "asc".equals(direction) ? Sort.Direction.ASC : Sort.Direction.DESC, request.getParameter("sidx"));
                } else {
                    pageRequest = new PageRequest(page, rows);
                }
            } catch (NumberFormatException ex) {
                //do nothing
            }
        }
        String filterName=request.getParameter("directoryName");
        if(pageRequest!=null){
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(completenessRepository.findByDirectoryNameContains(filterName, pageRequest));
            } else
                return new JGridRowsResponse<>(completenessRepository.findAll(pageRequest));
        } else {
            if(filterName!=null && !filterName.isEmpty()){
                return new JGridRowsResponse<>(completenessRepository.findByDirectoryNameContains(filterName));
            } else
                return new JGridRowsResponse<>(completenessRepository.findAll());
        }
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/edit", method = {RequestMethod.POST,RequestMethod.GET})
    public void editor(@RequestParam String oper,@RequestParam(required = false) Long id, Completeness completeness,BindingResult result,HttpServletResponse response)throws IOException {
        if(result.hasErrors()){
            response.sendError(400,result.toString());
            return;
        }
        switch (oper){
            case "add":
                completenessRepository.save(completeness);
                response.setStatus(200);
                break;
            case "edit":
                Completeness fromDB= completenessRepository.getOne(completeness.getDirectoryId());
                if(fromDB!=null) {
                    if(completeness.getVersion()>=fromDB.getVersion()) {
                        completenessRepository.save(completeness);
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
                    completenessRepository.delete(id);
                    response.setStatus(200);
                }
                break;
            default:
                response.sendError(406,"UNKNOWN OPERATION");
        }
    }
}
