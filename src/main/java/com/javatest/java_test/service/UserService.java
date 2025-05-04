package com.javatest.java_test.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javatest.java_test.entity.User;
import com.javatest.java_test.entity.UserInterface;
import com.javatest.java_test.exception.AuthenticationException;
import com.javatest.java_test.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInterface authenticateUser(String email, String password) throws AuthenticationException{
        if (email == null || email.trim().isEmpty()){
            throw new AuthenticationException("Email cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()){
            throw new AuthenticationException("Password cannot be null or empty");
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new AuthenticationException("User not found with email: " + email);

        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new AuthenticationException("Invalid password for user: " + email);
        }
        return user;
    }

}
