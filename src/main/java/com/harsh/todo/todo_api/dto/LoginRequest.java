package com.harsh.todo.todo_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequest {
    @Schema(description = "Email of the user", example = "harsh@example.com")
    private String email;

    @Schema(description = "Password of the user", example = "strongpassword123")
    private String password;
}
