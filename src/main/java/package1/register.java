package package1;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Driver;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String dbURL = "jdbc:mysql://localhost:3306/mailer";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbURL, "root", "");
			String sql = "INSERT INTO users (name, userPass) VALUES (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, request.getParameter("username"));
			statement.setString(2, request.getParameter("userpass"));

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				out.println("A new user was inserted successfully!");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			out.println("Some error occured!");
			ex.printStackTrace();
		} finally {
			out.println("<a href=\"login.html\">Click here to login</a>");
		}
	}

}
