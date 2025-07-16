package com.harsh.todo.todo_api.service;

import com.harsh.todo.todo_api.dto.UserDTO;
import com.harsh.todo.todo_api.model.User;
import com.harsh.todo.todo_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // temporary, we'll hash it soon
        user.setRole("USER");

        return userRepository.save(user);
    }
}
