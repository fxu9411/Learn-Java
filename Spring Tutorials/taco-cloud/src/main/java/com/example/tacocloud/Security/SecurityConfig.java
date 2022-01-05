package com.example.tacocloud.Security;

import com.example.tacocloud.Data.UserRepository;
import com.example.tacocloud.Domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                    .mvcMatchers("/design", "/orders").hasRole("USER")
                    .anyRequest().permitAll()

                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .usernameParameter("user")
                        .usernameParameter("pwd")
                        .defaultSuccessUrl("/design", true)
                .and()
                    .oauth2Login()
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                .build();
    }
}
