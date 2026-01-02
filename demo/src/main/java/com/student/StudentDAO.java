package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() throws SQLException {
        List<Student> student = new ArrayList<>();
        String selectSQL = "SELECT name, email, year FROM student ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int year = resultSet.getInt("year");
                student.add(new Student(name, email, year));
            }
        }
        return student;
    }

    public void registerStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO student (name, email, year) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(insertSQL)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getYear());
            ps.executeUpdate();
        }
    }
}