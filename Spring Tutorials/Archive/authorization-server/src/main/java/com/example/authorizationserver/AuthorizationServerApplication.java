package com.example.authorizationserver;

import com.example.authorizationserver.Model.User;
import com.example.authorizationserver.Model.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(
            UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(
                    new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
            repo.save(
                    new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
        };
    }
}
