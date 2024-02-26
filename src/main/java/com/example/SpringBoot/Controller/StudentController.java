package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Student;
import com.example.SpringBoot.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students"; // Assuming you have a students.jsp or students.html in your views folder
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable("studentId") int studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "studentDetails"; // Assuming you have a studentDetails.jsp or studentDetails.html in your views folder
    }

    @PostMapping
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students"; // Redirect to the students page after adding the student
    }

    @PutMapping("/{studentId}")
    public String updateStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student updatedStudent) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setUsername(updatedStudent.getUsername());
            student.setPassword(updatedStudent.getPassword());
            studentService.updateStudent(student);
        }
        return "redirect:/students"; // Redirect to the students page after updating the student
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students"; // Redirect to the students page after deleting the student
    }
}
