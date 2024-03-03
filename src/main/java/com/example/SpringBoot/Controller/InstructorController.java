package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Student;
import com.example.SpringBoot.Security.Credentials;

import com.example.SpringBoot.Services.GradeServices;
import com.example.SpringBoot.Services.InstructorService;
import com.example.SpringBoot.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private GradeServices gradeServices;
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public String showLoginForm(){
        return "instructor";
    }

    @PostMapping("/grades")
    public String addGrade(@ModelAttribute("grade") Grade grade) {
        instructorService.addGrade(grade);
        return "redirect:/api/instructors";
    }

    @PostMapping("/grades/update")
    public ResponseEntity<String> updateGrade(@RequestParam("gradeId") int gradeId, @RequestParam("updatedGrade") double updatedGrade) {
        Grade grade = gradeServices.getGradeById(gradeId);
        if (grade != null) {
            grade.setGrade(updatedGrade);
            instructorService.updateGrade(grade);
            return ResponseEntity.ok("Grade updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/grades/delete")
    public ResponseEntity<String> deleteGrade(@RequestParam("gradeId") int gradeId) {
        Grade grade = gradeServices.getGradeById(gradeId);
        if (grade != null) {
            instructorService.deleteGrade(gradeId);
            return ResponseEntity.ok("Grade deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/grades")
    public String getGradesByStudentId(@RequestParam("studentId") int studentId, Model model) {
        try {
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                model.addAttribute("student", student);
                return "view-grades";
            } else {
                return "studentnotfound";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "studentnotfound";
        }
    }


    @GetMapping("/grade") // different mapping path for this method
    public String getGradeById(@RequestParam("gradeId") int gradeId, Model model) {
        try {
            Grade grade = gradeServices.getGradeById(gradeId);
            if (grade != null) {
                model.addAttribute("grade", grade);
                String mapRowString = "Details of mapRow method";
                model.addAttribute("mapRowString", mapRowString);
                return "gradesbyid";
            } else {
                return "gradesnotfound";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "gradesnotfound";
        }
    }


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
