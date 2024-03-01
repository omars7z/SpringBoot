package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Student;
import com.example.SpringBoot.Services.GradeServices;
import com.example.SpringBoot.Services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private GradeServices gradeServices;



    @GetMapping("")
    public String showStudentDashboard(){
        return "students";
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable("studentId") int studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "studentDetails"; //  studentDetails.html
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        double classAverage = gradeServices.getClassAverage();
        double classMedian = gradeServices.getClassMedian();
        Grade highestGrade = gradeServices.getHighestGrade();
        Grade lowestGrade = gradeServices.getLowestGrade();

        model.addAttribute("classAverage", classAverage);
        model.addAttribute("classMedian", classMedian);
        model.addAttribute("highestGrade", highestGrade);
        model.addAttribute("lowestGrade", lowestGrade);

        return "statistics"; // statistics.html in views folder
    }

}
//    @PostMapping
//    public String addStudent(@ModelAttribute("student") Student student) {
//        studentService.addStudent(student);
//        return "redirect:/students"; // Redirect to the students page after adding the student
//    }
//
//    @PutMapping("/{studentId}")
//    public String updateStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student updatedStudent) {
//        Student student = studentService.getStudentById(studentId);
//        if (student != null) {
//            student.setName(updatedStudent.getName());
//            student.setUsername(updatedStudent.getUsername());
//            student.setPassword(updatedStudent.getPassword());
//            studentService.updateStudent(student);
//        }
//        return "redirect:/students"; // Redirect to the students page after updating the student
//    }
//
//    @DeleteMapping("/{studentId}")
//    public String deleteStudent(@PathVariable("studentId") int studentId) {
//        studentService.deleteStudent(studentId);
//        return "redirect:/students"; // Redirect to the students page after deleting the student
//    }
