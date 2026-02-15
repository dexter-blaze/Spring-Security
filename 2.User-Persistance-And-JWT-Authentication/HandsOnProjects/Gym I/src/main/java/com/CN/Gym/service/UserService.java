package com.CN.Gym.service;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Role;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.repository.UserRepository;
import com.CN.Gym.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

        /*
        This is the service class for User, you need to complete the class by doing the following:
        a. Use appropriate annotations.
        b. Complete the methods given below.
        c. Autowire the necessary dependencies.
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserRequest userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        User user = User.builder().email(userRequest.getEmail()).age(userRequest.getAge())
                .gender(userRequest.getGender()).password(encodedPassword)
                .build();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if(userRequest.getUserType() != null) {
            if (userRequest.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
                roles.add(role);
                user.setRoles(roles);
            } else if (userRequest.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
                roles.add(role);
                user.setRoles(roles);
            } else {
                role.setRoleName("ROLE_CUSTOMER");
                roles.add(role);
                user.setRoles(roles);
            }
        }
        else {
            role.setRoleName("ROLE_CUSTOMER");
            roles.add(role);
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User not found");
        } else {
            return userRepository.findById(id).get();
        }
    }

    public void updateUser(UserRequest userRequest, Long id) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());

        User user = getUserById(id);
        user.setEmail(userRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setGender(userRequest.getGender());
        user.setAge(userRequest.getAge());

        userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = getUserById(id); //will throw exception if user dne
        userRepository.deleteById(id);
    }

    public void addWorkout(WorkoutDto workoutDto, Long userId) {
        User user = getUserById(userId);
        Workout workout = Workout.builder()
                .workoutName(workoutDto.getWorkoutName())
                .description(workoutDto.getDescription())
                .difficultyLevel(workoutDto.getDifficultyLevel())
                .duration(workoutDto.getDuration())
                .user(user) //
                .build();

        workoutRepository.save(workout);
    }
}
