package com.cn.hotel.security;

import com.cn.hotel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    public final UserRepository userRepository;

    public CustomUserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
