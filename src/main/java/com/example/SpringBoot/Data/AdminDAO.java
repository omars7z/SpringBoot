package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.User;

public interface AdminDAO {
    void addUser(User user);
    void deleteUser(int userId);
}
