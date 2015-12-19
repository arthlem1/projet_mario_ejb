package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class PartieTerminee
 */
@WebServlet("/PartieTerminee")
public class PartieTerminee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionParties gestionParties;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartieTerminee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudoVainqueur = gestionParties.recupererVainqueurDernierePartie();
		
		JSONObject vainqueur = new JSONObject();
		
		try {
			vainqueur.put("vainqueur", pseudoVainqueur);
			response.setContentType("application/json");
			response.getWriter().write(vainqueur.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
