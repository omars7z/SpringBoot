package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Data.GradesDAOImpl;
import com.example.SpringBoot.Entities.Grade;
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
    private GradeServices gradeService; // Assuming you have a service layer for handling business logic

    @GetMapping("/grades")
    public String getAllGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades"; // Assuming you have a grades.jsp or grades.html in your views folder
    }

    @GetMapping("/grades/{gradeId}")
    public String getGradeById(@PathVariable("gradeId") int gradeId, Model model) {
        Grade grade = gradeService.getGradeById(gradeId);
        model.addAttribute("grade", grade);
        return "gradeDetails"; // Assuming you have a gradeDetails.jsp or gradeDetails.html in your views folder
    }

    @PostMapping("/grades")
    public String addGrade(@ModelAttribute("grade") Grade grade) {
        gradeService.addGrade(grade);
        return "redirect:/grades"; // Redirect to the grades page after adding the grade
    }

    @PutMapping("/grades/{gradeId}")
    public String updateGrade(@PathVariable("gradeId") int gradeId, @ModelAttribute("grade") Grade updatedGrade) {
        Grade grade = gradeService.getGradeById(gradeId);
        if (grade != null) {
            grade.setGrade(updatedGrade.getGrade());
            gradeService.updateGrade(grade);
        }
        return "redirect:/grades"; // Redirect to the grades page after updating the grade
    }

    @DeleteMapping("/grades/{gradeId}")
    public String deleteGrade(@PathVariable("gradeId") int gradeId) {
        gradeService.deleteGrade(gradeId);
        return "redirect:/grades"; // Redirect to the grades page after deleting the grade
    }

    @GetMapping("/class-average")
    public ResponseEntity<Double> getClassAverage() {
        double classAverage = gradeService.getClassAverage();
        return ResponseEntity.ok(classAverage);
    }

    @GetMapping("/class-median")
    public ResponseEntity<Double> getClassMedian() {
        double classMedian = gradeService.getClassMedian();
        return ResponseEntity.ok(classMedian);
    }

    @GetMapping("/highest-grade")
    public ResponseEntity<Grade> getHighestGrade() {
        Grade highestGrade = gradeService.getHighestGrade();
        return ResponseEntity.ok(highestGrade);
    }

    @GetMapping("/lowest-grade")
    public ResponseEntity<Grade> getLowestGrade() {
        Grade lowestGrade = gradeService.getLowestGrade();
        return ResponseEntity.ok(lowestGrade);
    }

}
