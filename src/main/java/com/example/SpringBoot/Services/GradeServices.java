package com.example.SpringBoot.Services;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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


    public Statistics getStatistics() {
        List<Grade> grades  =  getAllGrades();
        Statistics statistics = new Statistics();

        List<Double> listOfGrades = new ArrayList<>();
        for(int i=0; i< grades.size(); i++){
            listOfGrades.add(grades.get(i).getGrade());
        }
        Collections.sort(listOfGrades);
        statistics.setMin(listOfGrades.get(0));
        statistics.setMax(listOfGrades.get(grades.size())-1);

        statistics.setMax(listOfGrades.get(grades.size())/2);
        double sum =0;
        for(int i=0; i< grades.size(); i++){
            sum += grades.get(i).getGrade();
        }
        statistics.setAverage(sum/2);

        return statistics;
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
//    public double getClassAverage() {
//        String query = "SELECT AVG(grade) FROM grades";
//        return jdbcTemplate.queryForObject(query, Double.class);
//    }
//
//    public double getClassMedian() {
//        // Assuming SQL query to calculate median, as it varies depending on database
//        String query = "SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY grade) FROM grades";
//        return jdbcTemplate.queryForObject(query, Double.class);
//    }
//
//    public Grade getHighestGrade() {
//        String query = "SELECT * FROM grades ORDER BY grade DESC LIMIT 1";
//        return jdbcTemplate.queryForObject(query, new GradeRowMapper());
//    }
//
//    public Grade getLowestGrade() {
//        String query = "SELECT * FROM grades ORDER BY grade ASC LIMIT 1";
//        return jdbcTemplate.queryForObject(query, new GradeRowMapper());
//    }
