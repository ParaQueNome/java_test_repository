package com.javatest.java_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatest.java_test.controller.AuthController;
import com.javatest.java_test.dto.LoginRequest;
import com.javatest.java_test.entity.User;
import com.javatest.java_test.entity.UserType;
import com.javatest.java_test.repository.UserRepository;
import com.javatest.java_test.service.JwtTokenService;
import com.javatest.java_test.service.UserService;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)

public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtTokenService jwtTokenService;

    
    @MockitoBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() throws Exception {
        LoginRequest request = new LoginRequest("user@example.com", "password");
        User mockUser = new User(1L, "user@example.com", "password", null, UserType.ADMIN);

        when(userService.authenticateUser("user@example.com", "password")).thenReturn(mockUser);
        when(jwtTokenService.generateToken(mockUser)).thenReturn("mocked-jwt-token");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("mocked-jwt-token"));
    }

    @Test
    public void testLogin_InvalidCredentials() throws Exception {
        LoginRequest request = new LoginRequest("wrong@example.com", "wrongpassword");

        when(userService.authenticateUser("wrong@example.com", "wrongpassword")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
            .andExpect(status().isUnauthorized());
    }
}
