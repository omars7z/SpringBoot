//package com.example.SpringBoot.Controller;
//
//import com.example.SpringBoot.Entities.Grade;
////import com.example.SpringBoot.Services.InstructorServices;
//import com.example.SpringBoot.Security.Credentials;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/api/instructor")
//public class InstructorController {
//
//    @Autowired
//    private InstructorServices instructorService;
//
//    @Autowired
//    private Credentials credentials;
//
//    @GetMapping("/add-grade")
//    public String showAddGradeForm(){
//        return "add-grade"; // Assuming you have an HTML template for adding grades
//    }
//
//    @PostMapping("/grades")
//    public String addGrade(@ModelAttribute("grade") Grade grade) {
//        instructorService.addGrade(grade);
//        return "redirect:/instructor"; // Redirect to instructor dashboard or any other page
//    }
//
//    @DeleteMapping("/grades/{gradeId}")
//    public String deleteGrade(@PathVariable("gradeId") int gradeId) {
//        instructorService.deleteGrade(gradeId);
//        return "redirect:/instructor"; // Redirect to instructor dashboard or any other page
//    }
//}
