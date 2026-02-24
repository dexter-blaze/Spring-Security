//package com.cn.hotel.controller;
//
//import com.cn.hotel.dto.JwtRequest;
//import com.cn.hotel.dto.JwtResponse;
//import com.cn.hotel.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    @Autowired
//    AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
//        return new ResponseEntity<>(authService.login(jwtRequest), HttpStatus.OK);
//    }
//}
