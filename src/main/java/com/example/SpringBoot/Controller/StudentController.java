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

    @GetMapping("/{studentId}/view-grades")
    public String viewGrades(@PathVariable("studentId") int studentId, Model model) {
        List<Grade> grades = gradeServices.getGradesByStudentId(studentId);
        model.addAttribute("grades", grades);
        return "view-grades"; //   view-grades.html
    }


    @GetMapping("/{studentId}/grades")
    public String getStudentGrades(@PathVariable("studentId") int studentId, Model model) {
        List<Grade> grades = gradeServices.getGradesByStudentId(studentId);
        model.addAttribute("grades", grades);
        return "gradesbyid"; // view-grades.html
    }

//    @GetMapping("/{studentId}/grades")
//    public String getStudentGrades(@PathVariable("studentId") int studentId, Model model) {
//        try {
//            List<Grade> grade = (List<Grade>) gradeServices.getGradesByStudentId(studentId);
//            if (grade != null) {
//                model.addAttribute("grade", grade);
//                String mapRowString = "Details of mapRow method";
//                model.addAttribute("mapRowString", mapRowString);
//                return "gradesbyid";
//            } else {
//                return "gradesnotfound";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "gradesnotfound";
//        }
//    }
    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        double classAverage = gradeServices.getClassAverage();
//        double classMedian = gradeServices.getClassMedian();
        Grade highestGrade = gradeServices.getHighestGrade();
        Grade lowestGrade = gradeServices.getLowestGrade();

        model.addAttribute("classAverage", classAverage);
//        model.addAttribute("classMedian", classMedian);
        model.addAttribute("highestGrade", highestGrade);
        model.addAttribute("lowestGrade", lowestGrade);

        return "statistics"; // statistics.html in views folder
    }
}
