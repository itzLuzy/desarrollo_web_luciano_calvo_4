package com.tarea4.tarea4.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tarea4.tarea4.models.Role;
import com.tarea4.tarea4.models.User;
import com.tarea4.tarea4.models.UserRepository;

@Configuration
public class DefaultUserInitializer {

    @Bean
    public CommandLineRunner createDefaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return _ -> {
            if (userRepository.findByUsername("admin") == null) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("examen"));
                user.setRole(Role.ROLE_ADMIN);
                userRepository.save(user);
                System.out.println("Default admin user created.");
            }
        };
    }
}
