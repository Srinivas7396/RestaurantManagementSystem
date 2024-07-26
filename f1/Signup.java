package f1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantmanagementsystemdb","root","tiger");
			String email=request.getParameter("email");
			String username=request.getParameter("susername");
			String password=request.getParameter("spassword");
			String confirmPassword=request.getParameter("confirmPassword");
			Statement st=con.createStatement();
	
			if(password.equals(confirmPassword))
			{
				st.executeUpdate("insert into signuptable1 values('"+email+"','"+username+"','"+password+"')");
                pw.println("""
                        <html>
                        <head>
                        <style>
                        body{
                        background-color:yellow;
                        text-align:center;
                        }
                        </style>
                        </head>
                            <body>
                                <h1>Account created successfully!</h1>
                                <form action='LOGINorSIGNUP.html'>
                                    <input type='submit' value='Login'>
                                </form>
                            </body>
                        </html>
                    """);
				
			}
			else
			{
				response.sendRedirect("LOGINorSIGNUP.html");
			}
			
		}
		catch(Exception e)
		{
			response.sendRedirect("LOGINorSIGNUP.html");
		}
		
	}

}
