package com.harsh.todo.todo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedTodoResponse {
    private List<TodoResponse> data;
    private int page;
    private int limit;
    private long total;
}