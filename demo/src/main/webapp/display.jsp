<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head><title>Registered Students</title></head>
<body>
    <center>
        <h2>Registered Students</h2>
        <table border="1">
            <tr><th>Name</th><th>Email</th><th>Year</th></tr>
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
            <% } } %>
        </table>
        <br><a href="index.jsp">Back to Registration</a>
    </center>
</body>
</html>