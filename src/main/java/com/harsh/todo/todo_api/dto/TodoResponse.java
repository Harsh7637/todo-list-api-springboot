package com.harsh.todo.todo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private String description;
    // Removed completed, createdAt, updatedAt to match requirements
}
