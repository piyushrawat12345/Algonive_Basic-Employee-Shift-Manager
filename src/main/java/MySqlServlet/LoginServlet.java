package MySqlServlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet("/login")


public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    String user = req.getParameter("username");
    String pass = req.getParameter("password");

    // 1. Move Connection outside try block to close it safely in finally
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Load Driver
    	Class.forName("com.mysql.cj.jdbc.Driver");

        // FIX: URL first, then "root", then "password"
        // Pattern: jdbc:mysql://localhost:3306/DATABASE_NAME, USERNAME, PASSWORD
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee_id", "root", "root123");

        // Ensure "useres" matches your table name exactly
        String query = "SELECT role FROM useres WHERE username=? AND password=?"; 
        ps = con.prepareStatement(query);

        // Now index 1 and 2 exist!
        ps.setString(1, user); 
        ps.setString(2, pass);

        rs = ps.executeQuery();

        if (rs.next()) {
            HttpSession session = req.getSession(); 
            String role = rs.getString("role");
            session.setAttribute("username", user);
            session.setAttribute("role", role);

            if ("admin".equalsIgnoreCase(role)) {
                res.sendRedirect("AddShift.html");
            } else {
                res.sendRedirect("viewShift.html");
            }
        } else {
            
            res.sendRedirect("login.html?error=invalid");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        
        res.getWriter().println("Internal Error: " + e.getMessage());
    } finally {
       
        try { if(rs != null) rs.close(); if(ps != null) ps.close(); if(con != null) con.close(); } 
        catch (SQLException e) { e.printStackTrace(); }
    }
}
	}

        
