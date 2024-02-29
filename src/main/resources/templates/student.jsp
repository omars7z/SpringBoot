<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Grades</title>
</head>
<body>
<h1>Welcome, ${user.username}!</h1>
<h2>Your Grades</h2>
<!-- Display student's grades here -->
<table border="1">
    <thead>
    <tr>
        <th>Course</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through grades and display -->
    <c:forEach items="${grades}" var="grade">
        <tr>
            <td>${grade.courseName}</td>
            <td>${grade.grade}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Grades Statistics</h2>
<!-- Display statistics like class average, median, highest, and lowest marks here -->
<p>Class Average: ${classAverage}</p>
<p>Median: ${median}</p>
<p>Highest Mark: ${highestMark}</p>
<p>Lowest Mark: ${lowestMark}</p>
</body>
</html>
