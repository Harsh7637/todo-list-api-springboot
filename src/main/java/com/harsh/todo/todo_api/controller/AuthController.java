package com.harsh.todo.todo_api.controller;

import com.harsh.todo.todo_api.dto.LoginRequest;
import com.harsh.todo.todo_api.dto.LoginResponse;
import com.harsh.todo.todo_api.dto.UserDTO;
import com.harsh.todo.todo_api.model.User;
import com.harsh.todo.todo_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            String token = userService.registerUser(userDTO);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = userService.loginUser(request.getEmail(), request.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
