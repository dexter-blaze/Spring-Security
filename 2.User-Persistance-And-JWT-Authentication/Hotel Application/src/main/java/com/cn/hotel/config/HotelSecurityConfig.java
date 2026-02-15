package com.cn.hotel.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableAutoConfiguration
@EnableMethodSecurity(prePostEnabled = true) //@EnableGlobalMethodSecurity() is deprecated
public class HotelSecurityConfig {

    public final UserDetailsService userDetailsService; //5
    public HotelSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/register").permitAll() //antMatchers() removed in spring security version 6.0, this is replacement
                .anyRequest()
                .authenticated()
                .and()
                .rememberMe().userDetailsService(userDetailsService) //5
                .and()
                .formLogin() //1
                .loginPage("/login").permitAll() //4
                .and()
                .logout().deleteCookies("remember-me"); //6
        return  http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
