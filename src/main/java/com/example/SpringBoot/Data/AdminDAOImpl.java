package com.example.SpringBoot.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SpringBoot.Entities.User;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(query, userId);
    }
}
