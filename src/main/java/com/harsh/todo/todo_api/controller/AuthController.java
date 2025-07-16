package com.harsh.todo.todo_api.controller;

import com.harsh.todo.todo_api.dto.UserDTO;
import com.harsh.todo.todo_api.model.User;
import com.harsh.todo.todo_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Auth endpoint is reachable!");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User createdUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
}