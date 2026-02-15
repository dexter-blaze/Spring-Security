package com.CN.Gym.service;

import com.CN.Gym.dto.JwtRequest;
import com.CN.Gym.dto.JwtResponse;
import com.CN.Gym.jwt.JwtAuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationHelper jwtHelper;

    public JwtResponse login(JwtRequest jwtRequest) {
        this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String newToken = jwtHelper.generateToken(userDetails);

        return JwtResponse.builder()
                .jwtToken(newToken)
                .build();
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationObject = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authenticationObject);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
