package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Services.GradeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/dao")
public class GradeController {

    @Autowired
    private GradeServices gradeServices;

    @GetMapping("/grades")
    public String getAllGrades(Model model) {
        List<Grade> grades = gradeServices.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades"; // grades.html in views folder
    }

    @GetMapping("/gradeByIdForm")
    public String showGradeByIdForm(Model model) {
        return "gradeByIdForm";
    }

    @PostMapping("/grades")
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

    @GetMapping("/grades/{gradeId}")
    public String getGradeByIdPathVariable(@PathVariable int gradeId, Model model) {
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
}
