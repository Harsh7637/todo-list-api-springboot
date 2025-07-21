package com.harsh.todo.todo_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoDTO {
    @Schema(description = "Title of the todo item", example = "Complete Swagger integration")
    private String title;

    @Schema(description = "Description of the task", example = "Add OpenAPI annotations and secure endpoints")
    private String description;

    @Schema(description = "Is the task completed?", example = "false")
    private boolean completed = false;
}
