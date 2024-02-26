package com.example.SpringBoot.Services;

import com.example.SpringBoot.Entities.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class GradeServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Grade> getAllGrades() {
        String query = "SELECT * FROM grades";
        return jdbcTemplate.query(query, new GradeRowMapper());
    }

    public Grade getGradeById(int gradeId) {
        String query = "SELECT * FROM grades WHERE grade_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{gradeId}, new GradeRowMapper());
    }

    public void addGrade(Grade grade) {
        String query = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, grade.getStudentId(), grade.getCourseId(), grade.getGrade());
    }

    public void updateGrade(Grade grade) {
        String query = "UPDATE grades SET student_id = ?, course_id = ?, grade = ? WHERE grade_id = ?";
        jdbcTemplate.update(query, grade.getStudentId(), grade.getCourseId(), grade.getGrade(), grade.getGradeId());
    }

    public void deleteGrade(int gradeId) {
        String query = "DELETE FROM grades WHERE grade_id = ?";
        jdbcTemplate.update(query, gradeId);
    }

    private static class GradeRowMapper implements RowMapper<Grade> {
        @Override
        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Grade grade = new Grade();
            grade.setGradeId(rs.getInt("grade_id"));
            grade.setStudentId(rs.getInt("student_id"));
            grade.setCourseId(rs.getInt("course_id"));
            grade.setGrade(rs.getDouble("grade"));
            return grade;
        }
    }
}
