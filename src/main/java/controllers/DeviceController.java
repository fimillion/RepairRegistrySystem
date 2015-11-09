package controllers;


import model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.DeviceRepository;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;


    @RequestMapping("listDevices")
    @ResponseBody
    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }
}
