package com.harsh.todo.todo_api.controller;

import com.harsh.todo.todo_api.dto.TodoDTO;
import com.harsh.todo.todo_api.dto.TodoResponse;
import com.harsh.todo.todo_api.dto.PaginatedTodoResponse;
import com.harsh.todo.todo_api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    // Create a new todo
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            TodoResponse todo = todoService.createTodo(todoDTO);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Unauthorized")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Unauthorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // Get all todos with pagination
    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            PaginatedTodoResponse response = todoService.getAllTodosPaginated(page, limit);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    // Get a specific todo by ID
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        try {
            TodoResponse todo = todoService.getTodoById(id);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Todo not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            if (e.getMessage().contains("access denied")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Forbidden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }
            Map<String, String> error = new HashMap<>();
            error.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    // Update a todo
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        try {
            TodoResponse todo = todoService.updateTodo(id, todoDTO);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Todo not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            if (e.getMessage().contains("access denied")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Forbidden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }
            Map<String, String> error = new HashMap<>();
            error.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    // Delete a specific todo - Return 204 No Content
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Todo not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            if (e.getMessage().contains("access denied")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Forbidden");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            }
            Map<String, String> error = new HashMap<>();
            error.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}