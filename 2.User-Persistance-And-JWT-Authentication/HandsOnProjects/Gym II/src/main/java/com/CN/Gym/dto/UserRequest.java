package com.CN.Gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String email;
    private String password;
    private int age;
    private String gender;
    private String userType;

}
