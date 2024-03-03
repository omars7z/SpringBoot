package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.User;

public interface LoginDAO {
    User authenticate(String username, String password);
    User getAdminByUsername(String username);
    User getInstructorByUsername(String username);
}
