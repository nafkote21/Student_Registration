<!DOCTYPE html>
<html>
<head>
    <title>Student System</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Register Student</h2>
    <form action="register" method="post">
        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Year: <input type="number" name="year" required><br><br>
        <input type="submit" value="Register">
    </form>
    <br>
    <a href="show_all">View All Registered Students</a>
</body>
</html>