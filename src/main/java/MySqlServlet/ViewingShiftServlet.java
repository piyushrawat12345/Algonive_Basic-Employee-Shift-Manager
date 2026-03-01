package MySqlServlet;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;



@WebServlet("/viewShift")
public class ViewingShiftServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false); // Use false to avoid creating a new session
        String user = (session != null) ? (String) session.getAttribute("username") : null;

        // Redirect to login if user is not logged in
        if (user == null) {
            res.sendRedirect("login.html");
            return;
        }

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // FIX: Correct Password
            Connection con = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/Employee_id", "root", "root123");

            // Order the shifts by date so the employee sees the nearest one first
            PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM shiftes WHERE username=? ORDER BY shift_date ASC");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            out.println("<html><head><title>Your Schedule</title></head><body>");
            out.println("<h2>Welcome, " + user + "!</h2>");
            out.println("<h3>Your Upcoming Shifts:</h3>");
            out.println("<table border='1'><tr><th>Date</th><th>Start</th><th>End</th></tr>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("shift_date") + "</td>");
                out.println("<td>" + rs.getString("start_time") + "</td>");
                out.println("<td>" + rs.getString("end_time") + "</td>");
                out.println("</tr>");
            }

            if (!found) {
                out.println("<tr><td colspan='3'>No shifts assigned yet.</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='addShift.html'>Logout</a>");
            out.println("</body></html>");

            con.close(); // Clean up
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error loading shifts: " + e.getMessage());
        }
    }
}