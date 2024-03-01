package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.GradesDAOImpl;
import com.example.SpringBoot.Data.StudentDAO;
import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private GradesDAOImpl gradesDAOImpl;

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    public Grade getGradeById(int gradeId) {
        return gradesDAOImpl.getGradeById(gradeId);
    }

    public double getClassAverage() {
        return gradesDAOImpl.getClassAverage();
    }

    public double getClassMedian() {
        return gradesDAOImpl.getClassMedian();
    }

    public Grade getHighestGrade() {
        return gradesDAOImpl.getHighestGrade();
    }

    public Grade getLowestGrade() {
        return gradesDAOImpl.getLowestGrade();
    }
}
