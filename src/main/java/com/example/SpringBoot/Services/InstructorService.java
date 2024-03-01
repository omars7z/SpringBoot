package com.example.SpringBoot.Services;

import com.example.SpringBoot.Data.GradesDAOImpl;
import com.example.SpringBoot.Entities.Grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private GradesDAOImpl gradesDAOImpl;

    public void addGrade(Grade grade) {
        gradesDAOImpl.addGrade(grade);
    }

    public void updateGrade(Grade grade) {
        gradesDAOImpl.updateGrade(grade);
    }

    public void deleteGrade(int gradeId) {
        gradesDAOImpl.deleteGrade(gradeId);
    }
}
