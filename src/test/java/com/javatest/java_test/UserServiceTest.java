package com.javatest.java_test;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javatest.java_test.entity.User;
import com.javatest.java_test.entity.UserType;
import com.javatest.java_test.exception.AuthenticationException;
import com.javatest.java_test.repository.UserRepository;
import com.javatest.java_test.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser_Success() {
        String email = "test@example.com";
        String password = "test123";
        String encodedPassword = "encodedPassword";

        User user = new User(1L, "Test", email, encodedPassword, UserType.COLLABORATOR);

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        assertDoesNotThrow(() -> {
            var result = userService.authenticateUser(email, password);
            assertEquals(email, result.getEmail());
        });
    }

    @Test
    void testAuthenticateUser_WrongPassword() {
        when(userRepository.findByEmail("user@example.com"))
                .thenReturn(new User(1L, "Name", "user@example.com", "encoded", UserType.ADMIN));
        when(passwordEncoder.matches("wrong", "encoded")).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> {
            userService.authenticateUser("user@example.com", "wrong");
        });
    }
}
