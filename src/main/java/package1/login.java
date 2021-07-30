package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			// loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailer", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where name='" + request.getParameter("username") + "' and userPass='" + request.getParameter("userpass") + "'");
		String name="";
		while (rs.next())
	    name = rs.getString(2);
	    System.out.println(name);
	    if(name.equals(request.getParameter("username"))) {
	    	out.println("Welcome " + name + "!");
	    	request.getRequestDispatcher("Welcome").forward(request, response);
	    }else{
	    	
	    }
		con.close();
		} catch (Exception e) {
			out.println("Some error occured!");
			System.out.println(e);
		}finally {
			out.println("<a href=\"register.html\">Click here to register</a>");
		}
		
	}

}
