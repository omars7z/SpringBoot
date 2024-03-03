package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Student;
import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Security.Authentication;
import com.example.SpringBoot.Services.LoginService;
import com.example.SpringBoot.Services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private Authentication authentication;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public String handleLogin(String username, String password, Model model, HttpServletRequest request) {
        User user = loginService.authenticate(username, password);
        if (user != null && user.getRole().equals("Student")) {
            HttpSession session = request.getSession();
            int studentId = determineStudentId();
            session.setAttribute("studentId", studentId);
            authentication.setAuthenticatedUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("id", authentication.getAuthenticatedId());
            return "students";
        } else {
            String redirectUrl = determineUrl(user != null ? user.getRole() : "fail-login");
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/fail-login")
    public String showFailLoginForm(){
        return "fail-login";
    }


    private int determineStudentId() {
        String username = authentication.getAuthenticatedUsername(); // Get the authenticated username
        Student student = studentService.getStudentByUsername(username);
        return student != null ? student.getStudentId() : -1;
    }


    private String determineUrl(String role) {
        return switch (role) {
            case "Student" -> "/api/students";
            case "Instructor" -> "/api/instructors";
            case "Admin" -> "/api/admin";
            default -> "/";
        };
    }
}
