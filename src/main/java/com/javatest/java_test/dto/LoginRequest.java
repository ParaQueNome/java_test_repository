package com.javatest.java_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
