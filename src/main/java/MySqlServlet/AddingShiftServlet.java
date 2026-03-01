package MySqlServlet;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AddShift")

public class AddingShiftServlet extends HttpServlet {
	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	        try {
	        	
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	              "jdbc:mysql://localhost:3306/Employee_id","root","root123");

	            PreparedStatement ps = con.prepareStatement(
	              "INSERT INTO shiftes(username,shift_date,start_time,end_time) VALUES (?,?,?,?)");

	            ps.setString(1, req.getParameter("username"));
	            ps.setString(2, req.getParameter("shift_date"));
	            ps.setString(3, req.getParameter("start"));
	            ps.setString(4, req.getParameter("end"));

	            ps.executeUpdate();
	            res.getWriter().println("Shift Added Successfully");
	        } catch (Exception e) {
	            e.printStackTrace();
	            res.getWriter().println("Error adding shift ❌");
	        }
	    }
	}
	