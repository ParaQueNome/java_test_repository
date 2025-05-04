package com.javatest.java_test.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImpl implements UserDetails {
    
    private UserInterface userInterface;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userInterface.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userInterface.getPassword();
    }

    @Override
    public String getUsername() {
        return userInterface.getName();
    }
    
}
