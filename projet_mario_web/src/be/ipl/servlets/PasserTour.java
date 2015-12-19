package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class PasserTour
 */
@WebServlet("/PasserTour")
public class PasserTour extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionParties gestionParties;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasserTour() {
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
		
		gestionParties.joueurSuivant(partieEnCours);
		
		
	}

}
