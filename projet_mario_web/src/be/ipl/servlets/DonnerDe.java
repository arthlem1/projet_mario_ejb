package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionJoueurs;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class DonnerDe
 */
@WebServlet("/DonnerDe")
public class DonnerDe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionParties gestionParties;
	@EJB
	private GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonnerDe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Joueur joueur = (Joueur) session.getAttribute("joueur");

		Partie partieEnCours = gestionParties.getPartieEnCours();

		String pseudo = request.getParameter("pseudo");

		Joueur receveur = gestionJoueurs.rechercher(pseudo);
		JSONObject resultat = new JSONObject();
		
		if (receveur != null) {
			gestionJoueurPartie.donnerDe(joueur, receveur, 1, partieEnCours);
			try {
				resultat.put("success", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				resultat.put("success", 0).put("message", "joueur inexistant");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.setContentType("application/json");
		response.getWriter().write(resultat.toString());

	}

}
