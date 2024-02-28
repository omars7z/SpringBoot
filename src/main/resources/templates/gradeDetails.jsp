<jsp:useBean id="student" scope="request" type="com.example.SpringBoot.Entities.Student"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
</head>
<body>
<h1>Student Details</h1>
<table border="1">
    <tr>
        <th>Student ID</th>
        <th>Name</th>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <tr>
        <td>${student.studentId}</td>
        <td>${student.name}</td>
        <td>${student.username}</td>
        <td>${student.password}</td>
    </tr>
</table>
</body>
</html>
