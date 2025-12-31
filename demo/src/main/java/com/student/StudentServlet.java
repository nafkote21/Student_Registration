package com.student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private studentDB studentDB;

    @Override
    public void init() throws ServletException {
        studentDB = new studentDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> studentList = studentDB.getAllStudents();
            request.setAttribute("students", studentList);
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
            request.setAttribute("errorMessage", "Database error: Could not retrieve students.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        if (name != null && !name.trim().isEmpty() && email != null && !email.trim().isEmpty()) {
            Student newStudent = new Student(name, email);
            try {
                studentDB.addStudent(newStudent);
            } catch (SQLException e) {
                System.err.println("Error adding student: " + e.getMessage());
                request.setAttribute("errorMessage", "Database error: Could not add student.");
                doGet(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "Name and Email cannot be empty.");
            doGet(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/student");
    }
}