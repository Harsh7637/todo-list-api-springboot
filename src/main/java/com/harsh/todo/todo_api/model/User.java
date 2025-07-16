package com.harsh.todo.todo_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // ✅ Renaming the table to avoid conflict
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "USER";
}
