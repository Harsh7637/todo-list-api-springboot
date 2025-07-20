package com.harsh.todo.todo_api.service;

import com.harsh.todo.todo_api.dto.UserDTO;
import com.harsh.todo.todo_api.model.User;
import com.harsh.todo.todo_api.repository.UserRepository;
import com.harsh.todo.todo_api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        // Generate and return token immediately after registration
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return jwtService.generateToken(userDetails);
    }

    public String loginUser(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtService.generateToken(userDetails);
    }
}