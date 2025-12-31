CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    year int default null
);

