package MySqlServlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Employee_id", "root", "root123");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO useres(username,password,role,fullname) VALUES (?,?,?,?)");

            ps.setString(1, req.getParameter("username"));
            ps.setString(2, req.getParameter("password"));
            ps.setString(3, req.getParameter("role"));
            ps.setString(4, req.getParameter("fullname"));

            ps.executeUpdate();
            res.getWriter().println("Registration Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}