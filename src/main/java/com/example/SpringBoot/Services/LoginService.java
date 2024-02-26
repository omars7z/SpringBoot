package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.LoginDAO;
import com.example.SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDAO loginDAO;

    public User authenticate(String username, String password) {
        return loginDAO.authenticate(username, password);
    }
}
