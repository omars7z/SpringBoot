package com.example.SpringBoot.Entities;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
public class Grade {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;
    private int studentId;
    private int courseId;
    private double grade;


    // Constructors
    public Grade() {
    }

    public Grade(int studentId, int courseId, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    // toString method
    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", grade=" + grade +
                '}';
    }
}
