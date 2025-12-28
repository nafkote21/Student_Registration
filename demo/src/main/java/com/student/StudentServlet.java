package com.student;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = { "/register", "/show_all" })
public class StudentServlet extends HttpServlet {
    private String dbUrl = "jdbc:mysql://localhost:3306/students_db";
    private String dbUser = "root";
    private String dbPass = "123456"; // Use the password from your Workbench login

    // Handles Student Registration (POST /register)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
                String sql = "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setInt(3, year);
                stmt.executeUpdate(); // [cite: 15]
                response.sendRedirect("show_all");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handles Viewing All Students (GET /show_all)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String, String>> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students"); // [cite: 17]
                while (rs.next()) {
                    Map<String, String> s = new HashMap<>();
                    s.put("name", rs.getString("name"));
                    s.put("email", rs.getString("email"));
                    s.put("year", rs.getString("year"));
                    students.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("studentList", students);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }
}