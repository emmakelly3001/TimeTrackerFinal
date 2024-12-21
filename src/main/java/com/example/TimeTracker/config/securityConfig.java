package com.example.TimeTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class securityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                //.requestMatchers("/addUser","/html/**", "/css/**", "/js/**").permitAll()  // This line allows access to /addUser2
                .requestMatchers("/**").permitAll()  // This line allows access to /addUser2
                 .anyRequest().authenticated());

        return http.build();
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
