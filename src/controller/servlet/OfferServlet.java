package controller.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import controller.MyLogger;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class OfferServlet
 */
@WebServlet("/offer")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(OfferServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfferServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuctionRequestManager.makeOffer(request);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("auction");
			dispatcher.forward(request, response);
		} catch (SQLiteFailRequestException | InexistentTypeParameterException | FailRollBackException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		} catch (InsufficientRequirementsException e) {
			request.setAttribute("errorMessage", "Credito non sufficiente!");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("auction");
			dispatcher.forward(request, response);
		}
	}

}
