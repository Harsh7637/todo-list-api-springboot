package com.harsh.todo.todo_api.dto;

import lombok.Data;

@Data
public class TodoDTO {
    private String title;
    private String description;
    private boolean completed = false;
}
