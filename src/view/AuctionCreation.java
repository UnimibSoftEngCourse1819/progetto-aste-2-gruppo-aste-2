package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class AuctionCreation
 */
@WebServlet("/auctionCreation")
public class AuctionCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionCreation() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean successfulOperation = false;
		
		try {
			AuctionRequestManager.createAuction(request);
			successfulOperation = true;
			if(successfulOperation)
				response.sendRedirect("index.jsp");
			else
				response.sendRedirect("auctionCreation.jsp");
		} catch (SQLiteFailRequestException | InexistentTypeParameterException e) {
			// TODO manda un pop-up di fallimento
			e.printStackTrace();
		}
		if(successfulOperation) {
			/**
			 * TODO manda un pop-up di successo che chiede se vuole farne un'altra
			 * se risponde di si rimane nella pagina e pulisce i campi
			 * se risponde di no lo reindirizza nella pagina di home
			 */
		}
    }
}
