package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AuthenticationService;
import exception.MyConnectionException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AuthenticationService authenticationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        authenticationService = new AuthenticationService();
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
		try {
			if(authenticationService.authenticate(request)) {
				// Get the current session
				HttpSession oldSession = request.getSession(false);
				if(oldSession != null) {
					oldSession.invalidate(); // invalidates the current session if it exists
				}
				
				HttpSession currentSession = request.getSession(true); // create a new session
				currentSession.setAttribute("email", request.getParameter("email"));
				currentSession.setMaxInactiveInterval(5 * 60); // maximum five minutes of inactivity
				
				response.sendRedirect("index.jsp"); // got to the next page
			} 
			else {
				// If authentication fails
				response.sendRedirect("login.jsp");
			}
		} catch (MyConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
