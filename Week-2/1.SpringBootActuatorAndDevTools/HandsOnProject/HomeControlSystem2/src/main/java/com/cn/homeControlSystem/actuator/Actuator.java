package com.cn.homeControlSystem.actuator;

import com.cn.homeControlSystem.model.SmartDevice;
import com.cn.homeControlSystem.repositories.SmartDevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//add required annotations for this custom actuator endpoint class.
@Endpoint(id = "statusInfo")
@Component
public class Actuator {

    //Autowire the SmartDeviceRepository object.
    @Autowired
    SmartDevicesRepository smartDevicesRepository;

    Map<String, String> data = new HashMap<>();

/**
 * Complete the method body of by writing the necessary logic to fetch a map of all smart devices with their status in a key-value pair.
 * Add required annotation.
 */
    @ReadOperation
    public Map getDeviceStatus() {
        List<SmartDevice> smartDeviceList = smartDevicesRepository.findAll();
        for(SmartDevice s : smartDeviceList) {
            data.put(s.getName(), s.getStatus());
        }
        return data;
    }
}
