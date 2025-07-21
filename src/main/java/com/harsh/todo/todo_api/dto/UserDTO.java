package com.harsh.todo.todo_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
    @Schema(description = "User's full name", example = "Harsh Sharma")
    private String name;

    @Schema(description = "User's email address", example = "harsh@example.com")
    private String email;

    @Schema(description = "Password (will be hashed)", example = "strongpassword123")
    private String password;
}
