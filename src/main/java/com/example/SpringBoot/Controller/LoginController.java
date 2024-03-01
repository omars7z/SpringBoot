package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Security.Authentication;
import com.example.SpringBoot.Services.LoginService;
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

    //make a dao method find id by name, service as a bridge,
    //put servlet in parameter, getsession()

//    HttpSession session = request.getSession();
//    session.getAttribute("userId")
    @PostMapping("/login")
    public String handleLogin(String username, String password, Model model, HttpServletRequest request) {
        User user = loginService.authenticate(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
//            session.setAttribute("userId", userId);
            model.addAttribute("user", user);
            model.addAttribute("id", authentication.getAuthenticatedId());
            String redirectUrl = determineUrl(user.getRole());
            System.out.println(user.getRole());
            System.out.println(redirectUrl);
            return "redirect:" + redirectUrl; //redirect based on role
        } else {
            return "redirect:/fail-login";
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

    private String determineUrl(String role) {
        return switch (role) {
            case "Student" -> "/api/students";
            case "Instructor" -> "/api/instructors";
            case "Admin" -> "/api/admin";
            default -> "/";
        };
    }

}
