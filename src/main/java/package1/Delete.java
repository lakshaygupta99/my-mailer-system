package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			javax.servlet.http.Cookie[] ck = request.getCookies();
			System.out.println(ck[0].getValue());
			if(ck[0]!= null && ck[0].getValue() != null) {
				String dbURL = "jdbc:mysql://localhost:3306/mailer";
				try {
					Class.forName("com.mysql.jdbc.Driver");

					Connection con = DriverManager.getConnection(dbURL, "root", "");
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from mails where toMail='"+ck[0].getValue()+"'");
					out.println("<h1>Welcome to your Inbox!</h1>");
					out.println("<br/>");
					while (rs.next()) {
						out.println("<div>From:" + rs.getString(2) +  "</div>");
						out.println("<div>To:" + rs.getString(3) +  "</div>");
						out.println("<div>Subject:" + rs.getString(4) +  "</div>");
						out.println("<div>Body:" + rs.getString(5) +  "</div>");
						out.println("<br/>");
					}
					
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
				}
			}else {
				out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
			}
				
		}catch(Exception e) {
			
		}
		out.println("<br/>");
		
		try {
			javax.servlet.http.Cookie[] ck = request.getCookies();
			System.out.println(ck[0].getValue());
			if(ck[0]!= null && ck[0].getValue() != null) {
				String dbURL = "jdbc:mysql://localhost:3306/mailer";
				try {
					Class.forName("com.mysql.jdbc.Driver");

					Connection con = DriverManager.getConnection(dbURL, "root", "");
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from drafts where toMail='"+ck[0].getValue()+"'");
					out.println("<h1>Welcome to your Drafts!</h1>");
					out.println("<br/>");
					while (rs.next()) {
						out.println("<div>From:" + rs.getString(2) +  "</div>");
						out.println("<div>To:" + rs.getString(3) +  "</div>");
						out.println("<div>Subject:" + rs.getString(4) +  "</div>");
						out.println("<div>Body:" + rs.getString(5) +  "</div>");
						out.println("<br/>");
					}
					
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
				}
			}else {
				out.println("<a href = '/MailSystemExercise/login.html'>Click here to login! </a>");
			}
				
		}catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
