package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        return jdbcTemplate.query(query, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(int studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{studentId}, new StudentRowMapper());
    }

    @Override
    public Student getStudentByUsername(String username) {
        try {
            String query = "SELECT * FROM students WHERE username = ?";
            return jdbcTemplate.queryForObject(query, new Object[]{username}, new StudentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<String> getAllUsernames() {
        String query = "SELECT username FROM students";
        return jdbcTemplate.queryForList(query, String.class);
    }

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setStudentId(rs.getInt("student_id"));
            student.setName(rs.getString("name"));
            student.setUsername(rs.getString("username"));
            student.setPassword(rs.getString("password"));
            return student;
        }
    }
}
