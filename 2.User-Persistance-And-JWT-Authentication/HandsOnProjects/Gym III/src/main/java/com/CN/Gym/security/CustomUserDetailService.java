package com.CN.Gym.security;


import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	/*
	 1. Autowire the necessary dependencies and override the interface methods.
	 */
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return this.userRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException("No user found"));
    }
}
