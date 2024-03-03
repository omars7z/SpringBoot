package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.GradesDAOImpl;
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
    private GradesDAOImpl gradesDAOImpl;

    public List<Grade> getAllGrades() {
        return gradesDAOImpl.getAllGrades();
    }

    public Grade getGradeById(int gradeId) {
        return gradesDAOImpl.getGradeById(gradeId);
    }

    public List<Grade> getGradesByStudentId(int studentId) {
        return gradesDAOImpl.getGradesByStudentId(studentId);
    }

    public double getClassAverage() {
        return gradesDAOImpl.getClassAverage();
    }

    public double getClassMedian() {
        return getClassMedian();
    }

    public Grade getHighestGrade() {
        return gradesDAOImpl.getHighestGrade();
    }

    public Grade getLowestGrade() {
        return gradesDAOImpl.getLowestGrade();
    }


    public Statistics getStatistics() {
        List<Grade> grades  =  getAllGrades();
        Statistics statistics = new Statistics();

        List<Double> listOfGrades = new ArrayList<>();
        for (int i=0; i< grades.size(); i++) {
            listOfGrades.add(grades.get(i).getGrade());
        }
        Collections.sort(listOfGrades);
        statistics.setMin(listOfGrades.get(0));
        statistics.setMax(listOfGrades.get(grades.size())-1);
        statistics.setMax(listOfGrades.get(grades.size())/2);

        double sum =0;
        for (int i=0; i< grades.size(); i++){
            sum += grades.get(i).getGrade();
        }
        statistics.setAverage(sum/2);

        return statistics;
    }
    private double calculateMedian(List<Double> grades) {
        int size = grades.size();
        if (size % 2 == 0) {
            return (grades.get(size / 2 - 1) + grades.get(size / 2)) / 2.0;
        } else {
            return grades.get(size / 2);
        }
    }

//    private static class GradeRowMapper implements RowMapper<Grade> {
//        @Override
//        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Grade grade = new Grade();
//            grade.setGradeId(rs.getInt("grade_id"));
//            grade.setStudentId(rs.getInt("student_id"));
//            grade.setCourseId(rs.getInt("course_id"));
//            grade.setGrade(rs.getDouble("grade"));
//            return grade;
//        }
//    }
}
