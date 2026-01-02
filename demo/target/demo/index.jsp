<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #0056b3;
            text-align: center;
        }
        form {
            background: #e9f7ef;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
        }
        form div {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"] {
            width: calc(100% - 22px); 
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #218838;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Registration System</h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty successMessage}">
            <p style="color: #28a745; font-weight: bold; text-align: center; background-color: #d4edda; padding: 10px; border-radius: 5px; border: 1px solid #c3e6cb;">
        ${successMessage}
            </p> 
        </c:if>

        <h2>Register New Student</h2>

       <form action="${pageContext.request.contextPath}/register" method="post">
            <div>
                <label for="name">Student Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div>
                <label for="year">Student Year:</label>
                <input type="text" id="year" name="year" required>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit">Register Student</button>
        </form>

        <h2>Registered Students</h2>
        <c:choose>
            <c:when test="${not empty students}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Year</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.email}</td>
                                <td>${student.year}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No students registered yet.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>