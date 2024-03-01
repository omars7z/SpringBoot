package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Entities.Grade;
import com.example.SpringBoot.Entities.Statistics;
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

    @GetMapping("/grades/{gradeId}")
    public String getGradeById(@PathVariable int gradeId, Model model) {
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


//    @GetMapping("/statistics")
//    public String getStatistics(Model model) {
//        double classAverage = gradeServices.getClassAverage();
//        double classMedian = gradeServices.getClassMedian();
//        Grade highestGrade = gradeServices.getHighestGrade();
//        Grade lowestGrade = gradeServices.getLowestGrade();
//
//        model.addAttribute("classAverage", classAverage);
//        model.addAttribute("classMedian", classMedian);
//        model.addAttribute("highestGrade", highestGrade);
//        model.addAttribute("lowestGrade", lowestGrade);
//
//        return "statistics"; // statistics.html in views folder
//    }
}
