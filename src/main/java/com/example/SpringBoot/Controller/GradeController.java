package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Data.GradesDAOImpl;
import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Statistics;
import com.example.SpringBoot.Entities.User;
import com.example.SpringBoot.Services.GradeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/dao")
public class GradeController {

    @Autowired
    private GradeServices gradeService;

    @GetMapping("/grades")
    public String getAllGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades"; // grades.html in views folder
    }

//    @GetMapping("/grades/{gradeId}")
//    public ResponseEntity<Grade> getGradeById(@PathVariable int gradeId) {
//        Grade grade = gradeService.getGradeById(gradeId);
//        if (grade != null) {
//            return ResponseEntity.ok(grade);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/grades/{gradeId}")
    public String getGradeById(@PathVariable int gradeId, Model model) {
        try {
            Grade grade = gradeService.getGradeById(gradeId);
            if (grade != null) {
                model.addAttribute("grade", grade);
                return "gradesbyid";
            } else {
                return "gradesnotfound";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "gradesnotfound";
        }
    }


    @PostMapping("/grades")
    public String addGrade(@ModelAttribute("grade") Grade grade) {
        gradeService.addGrade(grade);
        return "redirect:/grades"; // Redirect to grades page after adding the grade
    }

    @PutMapping("/grades/{gradeId}")
    public String updateGrade(@PathVariable("gradeId") int gradeId, @ModelAttribute("grade") Grade updatedGrade) {
        Grade grade = gradeService.getGradeById(gradeId);
        if (grade != null) {
            grade.setGrade(updatedGrade.getGrade());
            gradeService.updateGrade(grade);
        }
        return "redirect:/grades"; // Redirect to grades page after updating the grade
    }

    @DeleteMapping("/grades/{gradeId}")
    public String deleteGrade(@PathVariable("gradeId") int gradeId) {
        gradeService.deleteGrade(gradeId);
        return "redirect:/grades"; // Redirect to grades page after deleting the grade
    }

    @GetMapping("/statistics")
    public Statistics getStatistics(){
        return gradeService.getStatistics();
    }
}
