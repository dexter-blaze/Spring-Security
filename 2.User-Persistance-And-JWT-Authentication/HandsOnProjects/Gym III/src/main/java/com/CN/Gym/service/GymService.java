package com.CN.Gym.service;


import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import com.CN.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {

    /*
        This is the service class for Gym, you need to complete the class by doing the following:
        a. Use appropriate annotations.
        b. Complete the methods given below.
        c. Autowire the necessary dependencies.
     */
    @Autowired
    GymRepository gymRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

     public Gym getGymById(Long id) {
         if (gymRepository.findById(id).isEmpty()) {
             throw new GymNotFoundException("Gym not found");
         } else {
             return gymRepository.findById(id).get();
         }
    }

    public void deleteGymById(Long id) {        
        Gym gym = getGymById(id);
        gymRepository.deleteById(id);
    }

    public void updateGym(GymDto gymDto, Long id) {
        Gym gym = getGymById(id);

        gym.setName(gymDto.getName());
        gym.setAddress(gymDto.getAddress());
        gym.setContactNo(gymDto.getContactNo());
        gym.setMembershipPlans(gymDto.getMembershipPlans());
        gym.setFacilities(gymDto.getFacilities());

        gymRepository.save(gym);
    }

    public void createGym(GymDto gymDto) {
        Gym gym = Gym.builder()
                .name(gymDto.getName())
                .address(gymDto.getAddress())
                .contactNo(gymDto.getContactNo())
                .membershipPlans(gymDto.getMembershipPlans())
                .facilities(gymDto.getFacilities())
                .build();

        gymRepository.save(gym);
    }

    public void addMember(Long userId, Long gymId) {
        User user = userService.getUserById(userId);
        Gym gym = getGymById(gymId);
        user.setGym(gym);

        userRepository.save(user);

    }

    public void deleteMember(Long userId, Long gymId) {
        User user = userService.getUserById(userId);
        user.setGym(null);
        userRepository.save(user);
    }
}
