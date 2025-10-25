package org.example.usermanagement.config;

import org.example.usermanagement.entity.User;
import org.example.usermanagement.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User u1 = new User();
        u1.setUsername("user1");
        u1.setEmail("user1@example.com");
        u1.setPassword("123456");
        u1.setCreated_at(LocalDateTime.now());

        User u2 = new User();
        u2.setUsername("user2");
        u2.setEmail("user2@example.com");
        u2.setPassword("123456");
        u2.setCreated_at(LocalDateTime.now());

        userRepository.save(u1);
        userRepository.save(u2);
    }
}
