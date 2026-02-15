package com.cn.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //makes this class follow builder design pattern .builder().{method chaining}.build() -> returns final object
public class JwtRequest {
    private String username;
    private String password;
}
