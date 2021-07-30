package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Compose
 */
@WebServlet("/Compose")
public class Compose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Compose() {
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
		PrintWriter out = response.getWriter();
		try {
			
			javax.servlet.http.Cookie[] ck = request.getCookies();
			System.out.println(ck[0].getValue());
			if(ck[0]!= null && ck[0].getValue() != null) {
				String dbURL = "jdbc:mysql://localhost:3306/mailer";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(dbURL, "root", "");
					String sql = "INSERT INTO mails (fromMail, toMail, subjectMail, bodyMail) VALUES (?, ?, ?, ?)";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, ck[0].getValue());
					statement.setString(2, request.getParameter("to"));

					statement.setString(3, request.getParameter("subject"));
					statement.setString(4, request.getParameter("body"));
					int rowsInserted = statement.executeUpdate();
					if (rowsInserted > 0) {
						System.out.println("Mail sent successfully!");
						out.println("<h1>Mail was sent successfully to " +  request.getParameter("to") + "</h1>");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
				}
			}else {
				out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
			}
		}catch(Exception e) {
			System.out.println(e);
			out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
		}
		
		out.println("<a href = 'Inbox'>Check your Inbox!</a>");
		
	}

}
