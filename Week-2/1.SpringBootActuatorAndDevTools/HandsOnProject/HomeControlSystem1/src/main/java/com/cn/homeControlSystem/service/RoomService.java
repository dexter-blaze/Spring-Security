package com.cn.homeControlSystem.service;

import com.cn.homeControlSystem.model.Room;
import com.cn.homeControlSystem.repositories.RoomRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    //autowire the RoomRepository object;

    @Autowired
    RoomRepository roomRepository;

    /**
     1. Complete the method body to fetch all room records.
     **/
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    /**
     1. Complete the method body to fetch a room record by id.
     **/
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).get();
    }
    /**
     1. Complete the method body to save a room record.
     **/
    public void addRoom(Room room) {
         roomRepository.save(room);
    }

    /**
     1. Complete the method body to delete a room record.
     **/
    @Transactional
    public void deleteRoom(Integer id) {
        Room room = getRoomById(id);
//        room.setSmartDeviceList(null);
        roomRepository.delete(room);
    }
}
