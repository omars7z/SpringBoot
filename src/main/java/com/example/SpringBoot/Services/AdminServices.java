package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.AdminDAO;
import com.example.SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    @Autowired
    private AdminDAO adminDAO;

    public void addUser(User user) {
        adminDAO.addUser(user);
    }

    public void deleteUser(int userId) {
        adminDAO.deleteUser(userId);
    }
}
