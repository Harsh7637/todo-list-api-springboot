package com.harsh.todo.todo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // ✅ Allow all /api/auth/* endpoints
                        .anyRequest().authenticated()  // ✅ Protect other routes
                )
                .formLogin(login -> login.disable())  // ❌ Disable default login form
                .httpBasic(httpBasic -> httpBasic.disable());  // ❌ Disable HTTP Basic Auth

        return http.build();
    }
}
