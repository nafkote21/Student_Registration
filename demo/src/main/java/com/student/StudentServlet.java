package com.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    // This handles the "Register Student" button (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            // 1. Save student using your DAO
            Student student = new Student(name, email, year);
            studentDAO.registerStudent(student);

            // 2. Set the Success Message
            request.setAttribute("successMessage", "The student registration IS SUCCESSFULLY done");

            // 3. Immediately refresh the list to show in the table
            doGet(request, response);

        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database Error: " + e.getMessage());
            doGet(request, response);
        }
    }

    // This handles loading the list (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch all students from your DAO
            List<Student> studentList = studentDAO.getAllStudents();
            
            // Send the list to the JSP (the attribute name "students" must match your JSP)
            request.setAttribute("students", studentList);
            
            // Forward to index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}