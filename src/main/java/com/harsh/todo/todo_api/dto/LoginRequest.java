package com.harsh.todo.todo_api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}