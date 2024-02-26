package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        User user = loginService.authenticate(username, password);
        if (user != null) {
            model.addAttribute("user", user);
            String redirectUrl = determineRedirectUrl(user.getRole());
            return "redirect:" + redirectUrl;
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Redirect back to login page with error message
        }
    }

    @GetMapping("/hello")
    public String login(){
        return "login";
    }

    private String determineRedirectUrl(String role) {
        switch (role) {
            case "student":
                return "/students";
            case "instructor":
                return "/instructors";
            case "admin":
                return "/admin";
            default:
                return "/";
        }
    }
}
