package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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

    private String determineRedirectUrl(String role) {
        switch (role) {
            case "student":
                return "/student";
            case "instructor":
                return "/instructor";
            case "admin":
                return "/admin";
            default:
                return "/";
        }
    }
}
