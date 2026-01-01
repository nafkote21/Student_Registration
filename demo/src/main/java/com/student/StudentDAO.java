package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Note: DatabaseConnection.getConnection() must be defined elsewhere in your
    // project
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        // 1. Added 'year' to the SELECT statement
        String selectSQL = "SELECT name, email, year FROM students ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                // 2. Removed 'id' variable to fix the "unused variable" warning
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int year = resultSet.getInt("year");

                // 3. Passes the data to the Student constructor
                students.add(new Student(name, email, year));
            }
        }
        return students;
    }

    public void registerStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(insertSQL)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getYear());
            ps.executeUpdate();
        }
    }
}