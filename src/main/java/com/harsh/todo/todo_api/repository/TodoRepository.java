package com.harsh.todo.todo_api.repository;

import com.harsh.todo.todo_api.model.Todo;
import com.harsh.todo.todo_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserOrderByCreatedAtDesc(User user);
    List<Todo> findByUserAndCompletedOrderByCreatedAtDesc(User user, boolean completed);
    Optional<Todo> findByIdAndUser(Long id, User user);
    long countByUser(User user);
    long countByUserAndCompleted(User user, boolean completed);

    // New method for pagination
    Page<Todo> findByUser(User user, Pageable pageable);
}