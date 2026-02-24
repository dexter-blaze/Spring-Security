package com.cn.homeControlSystem.service;

import com.cn.homeControlSystem.excpetion.InvalidStatusException;
import com.cn.homeControlSystem.dto.DeviceDTO;
import com.cn.homeControlSystem.model.Room;
import com.cn.homeControlSystem.model.SmartDevice;
import com.cn.homeControlSystem.repositories.RoomRepository;
import com.cn.homeControlSystem.repositories.SmartDevicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartDeviceService {
    private final SmartDevicesRepository smartDevicesRepository;

    public SmartDeviceService(SmartDevicesRepository smartDevicesRepository) {
        this.smartDevicesRepository = smartDevicesRepository;
    }

    //Autowire the SmartDevicesRepository object

    /**
     * 1. Complete the method body to fetch all device records.
     **/
    public List<SmartDevice> getAllDevices() {
        return smartDevicesRepository.findAll();
    }


    /**
     * 1. Complete the method body to fetch a device record by id.
     **/
    public SmartDevice getDeviceById(Integer id) {
        return smartDevicesRepository.findById(id).get();
    }


    /**
     * 1. Complete the method body to save a device record.
     **/
    public void addDevice(DeviceDTO deviceDTO) {
        SmartDevice smartDevice = new SmartDevice();

        smartDevice.setName(deviceDTO.getName());
        smartDevice.setType(deviceDTO.getType());
        smartDevice.setStatus(deviceDTO.getStatus());
        smartDevice.setRoomId(deviceDTO.getRoomId());

        smartDevicesRepository.save(smartDevice);
    }

    /**
     * 1. Complete the method body to update a device record's status (eg. from "On" to "Off").
     **/
    public void updateDeviceStatus(DeviceDTO deviceDTO) {
        SmartDevice sd = smartDevicesRepository.findByName(deviceDTO.getName());
        if (sd != null) {
            sd.setStatus(deviceDTO.getStatus());
            smartDevicesRepository.save(sd);
        }
    }


    //write the delete method here

    public void deleteDevice(Integer id) {
        smartDevicesRepository.deleteById(id);
    }
}
