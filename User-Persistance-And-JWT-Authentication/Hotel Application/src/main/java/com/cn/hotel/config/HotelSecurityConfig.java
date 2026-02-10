package com.cn.hotel.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableAutoConfiguration
@EnableMethodSecurity(prePostEnabled = true) //@EnableGlobalMethodSecurity() is deprecated
public class HotelSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
//                .requestMatchers("/hotel/create").hasRole("ADMIN") //antMatchers() removed in spring security version 6.0, this is replacement
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return  http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("tony")
                .password(passwordEncoder().encode("password"))
                .roles("NORMAL")
                .build();

        UserDetails user2 = User.builder()
                .username("steve")
                .password(passwordEncoder().encode("nopassword"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
