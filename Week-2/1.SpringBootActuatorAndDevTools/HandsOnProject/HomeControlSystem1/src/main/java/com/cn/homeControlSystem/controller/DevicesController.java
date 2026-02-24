package com.cn.homeControlSystem.controller;

import com.cn.homeControlSystem.dto.DeviceDTO;
import com.cn.homeControlSystem.excpetion.InvalidStatusException;
import com.cn.homeControlSystem.model.SmartDevice;
import com.cn.homeControlSystem.service.SmartDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DevicesController {

    //Autowire the SmartDeviceService object;
    @Autowired
    SmartDeviceService smartDeviceService;

    //complete the following method bodies as stated.

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping .
     **/
    @GetMapping("/all")
    public List<SmartDevice> getAllDevices() {
        return smartDeviceService.getAllDevices();
    }

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping and attach the required pathvariable to the method parameter.
     **/
    @GetMapping("/id/{id}")
    public SmartDevice getDeviceById(@PathVariable Integer id) {
        return smartDeviceService.getDeviceById(id);
    }

    /**
     1. Call the required service method
     2. Add proper annotation for POST Mapping and attach the required request body to the method parameter.
     3. Surround InvalidStatusException with try - catch.
     **/
    @PostMapping("/add")
    public void addDevice(@RequestBody DeviceDTO deviceDTO) throws InvalidStatusException {
        if ((deviceDTO.getStatus()).equalsIgnoreCase("on") || (deviceDTO.getStatus()).equalsIgnoreCase("off")) {
            smartDeviceService.addDevice(deviceDTO);
        } else {
            throw new InvalidStatusException("Invalid status");
        }
    }

    /**
     1. Call the required service method
     2. Add proper annotation for PUT Mapping and attach the required request body to the method parameter.
     3.  Surround InvalidStatusException with try - catch.
     **/
    @PutMapping("/changeStatus")
    public void updateDeviceStatus(@RequestBody DeviceDTO deviceDTO) throws InvalidStatusException {
        if ((deviceDTO.getStatus()).equalsIgnoreCase("on") || (deviceDTO.getStatus()).equalsIgnoreCase("off")) {
            smartDeviceService.updateDeviceStatus(deviceDTO);
        } else {
            throw new InvalidStatusException("Invalid status");
        }
    }

   // Create the new delete api later after running the application.
    @DeleteMapping("/delete/id/{id}")
    public void deleteDevice(@PathVariable Integer id) {
        smartDeviceService.deleteDevice(id);
    }
}
