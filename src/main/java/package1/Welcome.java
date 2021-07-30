package package1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
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
		java.io.PrintWriter out = response.getWriter(); 
		out.println("<html><head><title>My Mailer -  Welcome</title>"); 
		out.println("</head><body>"); 
		out.println("<h1>Welcome <I>" + request.getParameter("username") + "</I> to your mail!</h1>"  ); 
		Cookie ck = new Cookie("loginid", request.getParameter("username"));
		response.addCookie(ck);
		out.println("<br/>");out.println("<br/>");
		out.println("<a href = 'Inbox'>Inbox</a>");
		out.println("<a href = 'Compose.html'>Compose</a>");
		out.println("<a href = 'drafts.html'>Drafts</a>");
		out.println("<a href = 'Delete'>Delete</a>");
		out.println("<a href = 'Logout'>Logout</a>");
		out.println("</body></html>");
	}

}
