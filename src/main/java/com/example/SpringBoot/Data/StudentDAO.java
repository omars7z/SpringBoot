package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();

    Student getStudentById(int studentId);
}
