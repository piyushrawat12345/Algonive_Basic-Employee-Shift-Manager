# 🕒 Basic Employee Shift Manager

A simple web-based Employee Shift Management System built using Java (Servlet & JSP) and MySQL.
This application allows administrators to manage employee work shifts efficiently.

📌 Features

Add new employee shifts

View all shifts

Store shift data in MySQL database

Simple and clean web interface

Prevent invalid time entries

🛠️ Technologies Used

Java

Servlets

JSP

JDBC

MySQL

Apache Tomcat

HTML & CSS

Eclipse IDE

🗂️ Project Structure

Employee_Time/
│
├── src/
│   ├── LoginServlet.java
│   ├── RegisterServlet.java
│   ├── AddingShiftServlet.java
│   ├── ViewingShiftServlet.java
│
├── WebContent/
│   ├── login.html
│   ├── register.html
│   ├── AddShift.html
│   ├── viewShift.jsp
│
├── WEB-INF/
│   ├── web.xml
│
└── README.md

⚙️ Setup Instructions

1️⃣ Install Required Software

Install Java JDK

Install Apache Tomcat Server

Install MySQL Server

Install Eclipse IDE (Enterprise Edition)

2️⃣ Create Database

Open MySQL and run:

CREATE DATABASE shift_manager;

USE shift_manager;

CREATE TABLE shifts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    shift_date DATE,
    start_time TIME,
    end_time TIME
);

3️⃣ Add MySQL JDBC Driver

Download MySQL Connector J and:

Add it to your project
OR

Place it inside WEB-INF/lib

4️⃣ Configure Database Connection

In your servlet:

Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/shift_manager",
    "root",
    "your_password"
);

5️⃣ Run the Project

Right-click project → Run on Server

Select Apache Tomcat

Open browser:

http://localhost:8093/Employee_Time/
