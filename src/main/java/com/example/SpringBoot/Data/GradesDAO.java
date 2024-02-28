package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Grade;

import java.util.List;

public interface GradesDAO {
    List<Grade> getAllGrades();

    void addGrade(Grade grade);

    void updateGrade(Grade grade);

    void deleteGrade(int gradeId);

    Grade getGradeById(int gradeId);

    double getClassAverage();

    double getClassMedian();

    Grade getHighestGrade();

    Grade getLowestGrade();

}
