package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Services.AdminServices;
import com.example.SpringBoot.Security.Credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminServices adminService;

    @Autowired
    private Credentials credentials;

    @GetMapping("")
    public String showLoginForm(){
        return "admin";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user) {

        String password = user.getPassword();
        credentials.validatepassword(password);

        if (!credentials.isPasswordValid()) {
            return "redirect:/admin?error=" + credentials.getErrorMessage();
        }

        adminService.addUser(user);
        return "redirect:/api/admin"; // back to page
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        adminService.deleteUser(userId);
        return "redirect:/api/admin"; // back to page
    }
}
