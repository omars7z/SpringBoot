package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, student.getName(), student.getUsername(), student.getPassword());
    }

    @Override
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, username = ?, password = ? WHERE student_id = ?";
        jdbcTemplate.update(query, student.getName(), student.getUsername(), student.getPassword(), student.getStudentId());
    }

    @Override
    public void deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(query, studentId);
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
