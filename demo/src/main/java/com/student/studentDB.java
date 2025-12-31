package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class studentDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/students_db";
    private static final String JDBC_USER = "root"; 
    private static final String JDBC_PASSWORD = "123456"; 

    public studentDB() {
        System.out.println("Connected to MySQL Database: students_db");
    }

    public void addStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO students (name, email) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
             
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Student added: " + student.getName());
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            throw e; // Re-throw to be handled by the servlet
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String selectSQL = "SELECT id, name, email FROM students ORDER BY id";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                students.add(new Student(id, name, email));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
            throw e; // Re-throw to be handled by the servlet
        }
        return students;
    }
}