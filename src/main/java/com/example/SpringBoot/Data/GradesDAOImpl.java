package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GradesDAOImpl implements GradesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Grade> getAllGrades() {
        String query = "SELECT * FROM grades";
        return jdbcTemplate.query(query, new GradeRowMapper());
    }

    @Override
    public Grade getGradeById(int gradeId) {
        String sql = "SELECT * FROM grades WHERE grade_id = ?";
        List<Grade> grades = jdbcTemplate.query(sql, new Object[]{gradeId}, new GradeRowMapper());
        return grades.isEmpty() ? null : grades.get(0);
    }


    @Override
    public void addGrade(Grade grade) {
        String query = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, grade.getStudentId(), grade.getCourseId(), grade.getGrade());
    }

    @Override
    public void updateGrade(Grade grade) {
        String query = "UPDATE grades SET student_id = ?, course_id = ?, grade = ? WHERE grade_id = ?";
        jdbcTemplate.update(query, grade.getStudentId(), grade.getCourseId(), grade.getGrade(), grade.getGradeId());
    }

    @Override
    public void deleteGrade(int gradeId) {
        String query = "DELETE FROM grades WHERE grade_id = ?";
        jdbcTemplate.update(query, gradeId);
    }

    @Override
    public double getClassAverage() {
        String query = "SELECT AVG(grade) FROM grades";
        return jdbcTemplate.queryForObject(query, Double.class);
    }

    @Override
    public double getClassMedian() {
        // sort half and the one int hje middle
        String query = "SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY grade) FROM grades";
        return jdbcTemplate.queryForObject(query, Double.class);
    }

    @Override
    public Grade getHighestGrade() {
        String query = "SELECT * FROM grades ORDER BY grade DESC LIMIT 1";
        return jdbcTemplate.queryForObject(query, new GradeRowMapper());
    }

    @Override
    public Grade getLowestGrade() {
        String query = "SELECT * FROM grades ORDER BY grade ASC LIMIT 1";
        return jdbcTemplate.queryForObject(query, new GradeRowMapper());
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
