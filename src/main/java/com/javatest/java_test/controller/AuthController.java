package com.javatest.java_test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatest.java_test.dto.LoginRequest;
import com.javatest.java_test.dto.LoginResponse;
import com.javatest.java_test.entity.UserInterface;
import com.javatest.java_test.exception.AuthenticationException;
import com.javatest.java_test.service.JwtTokenService;
import com.javatest.java_test.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            UserInterface user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
            String token = jwtTokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token, user.getType()));
        }catch(AuthenticationException | JsonProcessingException | IllegalArgumentException | JWTCreationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
     
}
