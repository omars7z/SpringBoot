package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Security.Credentials;

import com.example.SpringBoot.Services.GradeServices;
import com.example.SpringBoot.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("")
    public String showLoginForm(){
        return "instructor";
    }

    @PostMapping("/grades")
    public String addGrade(@ModelAttribute("grade") Grade grade) {
        instructorService.addGrade(grade);
        return "redirect:/api/instructors";
    }

    @PutMapping("/grades/{gradeId}")
    public String updateGrade(@PathVariable("gradeId") int gradeId, @RequestBody Grade updatedGrade) {
        Grade grade = gradeServices.getGradeById(gradeId);
        if (grade != null) {
            grade.setGrade(updatedGrade.getGrade());
            instructorService.updateGrade(grade);
        }
        return "redirect:/api/instructors";
    }

    @DeleteMapping("/grades/{gradeId}")
    public String deleteGrade(@PathVariable("gradeId") int gradeId) {
        instructorService.deleteGrade(gradeId);
        return "redirect:/api/instructors";
    }

    @GetMapping("/grades") // Differentiate the mapping path for this method
    public String getGradesByStudentId(@RequestParam("studentId") int studentId, Model model) {
        List<Grade> grades = gradeServices.getGradesByStudentId(studentId);
        model.addAttribute("grades", grades);
        return "view-grades";
    }

    @GetMapping("/grade") // Different mapping path for this method
    public String getGradeById(@RequestParam("gradeId") int gradeId, Model model) {
        try {
            Grade grade = gradeServices.getGradeById(gradeId);
            if (grade != null) {
                model.addAttribute("grade", grade);
                String mapRowString = "Details of mapRow method"; // Populate mapRowString with actual details
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
