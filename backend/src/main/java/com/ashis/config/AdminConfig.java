package com.ashis.config;

import com.ashis.entities.User;
import com.ashis.repositories.UserRepository;
import com.ashis.utils.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin(){

        boolean existed = userRepository.existsByRole(Roles.ADMIN);

        if(!existed){

            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setRole(Roles.ADMIN);
            user.setCreatedAt(LocalDateTime.now());


            userRepository.save(user);

        }

    }
}
