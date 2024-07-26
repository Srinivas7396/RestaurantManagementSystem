package f1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantmanagementsystemdb", "root", "tiger");

            String lusername = request.getParameter("lusername");
            String lpassword = request.getParameter("lpassword");

            PreparedStatement ps = con.prepareStatement("select * from signuptable1 where username=? and password=?");
            ps.setString(1, lusername);
            ps.setString(2,lpassword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (lpassword.equals(rs.getString("password")) && lusername.equals(rs.getString("username"))) {
                    response.sendRedirect("index.html");
                } else {
                    pw.println("Incorrect password");
                }
            } else {
                pw.println("Account not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("LOGINorSIGNUP.html");
        }
    }
}
