package com.javatest.java_test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javatest.java_test.entity.User;
import com.javatest.java_test.entity.UserType;
import com.javatest.java_test.repository.UserRepository;

@SpringBootApplication
public class JavaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Evita duplicação
			if (userRepository.findByEmail("test@example.com")== null) {
				User user = new User();
				user.setName("Test User");
				user.setEmail("test@example.com");
				user.setPassword(passwordEncoder.encode("testpassword123"));
				user.setType(UserType.ADMIN); // ou ADMIN, dependendo do enum

				userRepository.save(user);
				System.out.println("Usuário de teste inserido.");
			}
		};
	}
}
