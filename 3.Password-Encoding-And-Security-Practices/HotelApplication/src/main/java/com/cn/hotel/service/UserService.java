package com.cn.hotel.service;

import com.cn.hotel.dto.UserRequest;
import com.cn.hotel.model.User;
import com.cn.hotel.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest userRequest) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Pbkdf2PasswordEncoder passwordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8(); //encoding pwd and storing in db while registration
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User newUser = new User();

        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
