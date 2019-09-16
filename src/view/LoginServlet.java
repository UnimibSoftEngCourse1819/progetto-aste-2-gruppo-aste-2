package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user = authenticationService.authenticate(request);
			if(user != null) {
				// Get the current session
				HttpSession oldSession = request.getSession(false);
				if(oldSession != null) {
					oldSession.invalidate(); // invalidates the current session if it exists
				}
				
				HttpSession currentSession = request.getSession(true); // create a new session
				currentSession.setAttribute("name", user);
				currentSession.setMaxInactiveInterval(5 * 60); // maximum five minutes of inactivity
				
				response.sendRedirect("index.jsp"); // got to the next page
			} 
			else {
				// If authentication fails
				request.setAttribute("errorMessage", "Email o password errati!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (MyConnectionException e) {
			e.printStackTrace();
		}
	}

}