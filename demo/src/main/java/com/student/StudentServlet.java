package com.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int year = Integer.parseInt(request.getParameter("year"));

        try {

            Student student = new Student(name, email, year);
            studentDAO.registerStudent(student);

            request.setAttribute("successMessage", "The student registration IS SUCCESSFULLY done");

            doGet(request, response);

        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database Error: " + e.getMessage());
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Student> studentList = studentDAO.getAllStudents();

            request.setAttribute("students", studentList);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}