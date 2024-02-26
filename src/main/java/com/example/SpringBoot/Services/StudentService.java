package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.StudentDAO;
import com.example.SpringBoot.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }
}
