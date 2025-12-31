package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String selectSQL = "SELECT id, name, email FROM students ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                students.add(new Student(id, name, email));
            }
        }
        return students;
    }
}