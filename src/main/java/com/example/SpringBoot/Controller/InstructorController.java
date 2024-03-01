package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Security.Credentials;

import com.example.SpringBoot.Services.GradeServices;
import com.example.SpringBoot.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
