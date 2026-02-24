package com.cn.homeControlSystem.controller;

import com.cn.homeControlSystem.model.Room;
import com.cn.homeControlSystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    //Autowire the RoomService object.
    @Autowired
    RoomService roomService;

    /**
     1. Call the required service method
     2. Add proper annotation for POST Mapping and attach the required request body to the method parameter.
     **/
    @PostMapping("/add")
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping and attach the required path-variable to the method parameter.
     **/
    @GetMapping("/id/{id}")
    public Room getRoomById(@PathVariable Integer id){
        return roomService.getRoomById(id);

    }

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping.
     **/
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     1. Call the required service method
     2. Add proper annotation for DELETE Mapping and attach the required path-variable to the method parameter.
     **/
    @DeleteMapping("/delete/id/{id}")
    public void deleteRoom(@PathVariable Integer id){
        roomService.deleteRoom(id);
    }

}
