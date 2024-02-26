package com.example.SpringBoot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Student Grading System!");
        return "home";
    }

    // Other controller methods for handling different requests
}

