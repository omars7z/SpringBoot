package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        List<User> users = jdbcTemplate.query(query, new Object[]{username, password}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getAdminByUsername(String username) {
        try {
            String query = "SELECT * FROM admins WHERE username = ?";
            return jdbcTemplate.queryForObject(query, new Object[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getInstructorByUsername(String username) {
        try {
            String query = "SELECT * FROM instructors WHERE username = ?";
            return jdbcTemplate.queryForObject(query, new Object[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
    }
}
