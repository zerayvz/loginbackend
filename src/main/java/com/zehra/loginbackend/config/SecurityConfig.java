package com.zehra.loginbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/ws/**").permitAll()
                        .requestMatchers("/api/rooms/**").permitAll()   // ✅ room create/join serbest
                        .anyRequest().permitAll()                       // (şimdilik hepsini açtık)
                );
        return http.build();
    }
}
