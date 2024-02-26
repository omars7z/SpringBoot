package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);

    Student getStudentById(int studentId);
}
