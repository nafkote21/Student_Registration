<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registered Students</title>
    <style>
        body { font-family: sans-serif; display: flex; flex-direction: column; align-items: center; }
        table { border-collapse: collapse; width: 50%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
        th { background-color: #f0f0f0; }
    </style>
</head>
<body>
    <h2>Registered Students</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Year</th>
        </tr>
        <% 
            List<Map<String, String>> list = (List<Map<String, String>>) request.getAttribute("studentList");
            if (list != null) {
                for (Map<String, String> s : list) {
        %>
        <tr>
            <td><%= s.get("name") %></td>
            <td><%= s.get("email") %></td>
            <td><%= s.get("year") %></td>
        </tr>
        <% 
                }
            } 
        %>
    </table>
    <br>
    <a href="index.jsp">Register New Student</a>
</body>
</html>