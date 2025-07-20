package com.harsh.todo.todo_api.service;

import com.harsh.todo.todo_api.dto.TodoDTO;
import com.harsh.todo.todo_api.dto.TodoResponse;
import com.harsh.todo.todo_api.dto.PaginatedTodoResponse;
import com.harsh.todo.todo_api.model.Todo;
import com.harsh.todo.todo_api.model.User;
import com.harsh.todo.todo_api.repository.TodoRepository;
import com.harsh.todo.todo_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private TodoResponse convertToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription()
        );
    }

    public TodoResponse createTodo(TodoDTO todoDTO) {
        User currentUser = getCurrentUser();

        Todo todo = new Todo();
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());
        todo.setUser(currentUser);

        Todo savedTodo = todoRepository.save(todo);
        return convertToResponse(savedTodo);
    }

    public PaginatedTodoResponse getAllTodosPaginated(int page, int limit) {
        User currentUser = getCurrentUser();

        // Convert page to 0-based index for Spring Data
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        Page<Todo> todoPage = todoRepository.findByUser(currentUser, pageable);

        List<TodoResponse> todos = todoPage.getContent().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PaginatedTodoResponse(todos, page, limit, todoPage.getTotalElements());
    }

    public TodoResponse getTodoById(Long id) {
        User currentUser = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));
        return convertToResponse(todo);
    }

    public TodoResponse updateTodo(Long id, TodoDTO todoDTO) {
        User currentUser = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }

    public void deleteTodo(Long id) {
        User currentUser = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));
        todoRepository.delete(todo);
    }
}