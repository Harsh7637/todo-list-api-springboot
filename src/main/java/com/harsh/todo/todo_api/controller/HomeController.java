package com.harsh.todo.todo_api.controller; // Make sure this package name matches your project

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // This tells the browser to redirect to your swagger-ui page.
        return "redirect:/swagger-ui.html";
    }
}