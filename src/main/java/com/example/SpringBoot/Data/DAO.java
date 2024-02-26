package com.example.SpringBoot.Data;

import com.example.SpringBoot.Entities.Grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<Grade> getAllGrades();

    void addGrade(Grade grade);

    void updateGrade(Grade grade);

    void deleteGrade(int gradeId);

    Grade getGradeById(int gradeId);

}
