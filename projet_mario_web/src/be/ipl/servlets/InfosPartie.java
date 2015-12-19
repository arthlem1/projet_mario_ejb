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
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.usecasesimpl.GestionPartiesImpl.Etat;

/**
 * Servlet implementation class InfosPartie
 */
@WebServlet("/InfosPartie")
public class InfosPartie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionParties gestionParties;
	@EJB
	private GestionJoueurPartie gestionJoueurPartie;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfosPartie() {
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
		
		JSONObject jsonObject = new JSONObject();

		if (partieEnCours != null) {

			JoueurPartie joueurCourant = partieEnCours.getJoueur_courant();

			boolean ton_tour = false;

			if (joueur.getId() == joueurCourant.getJoueur().getId()) {

				if (gestionJoueurPartie.lancerTour(joueur, partieEnCours)) {
					// C'est à son tour
					ton_tour = true;
				} else {
					ton_tour = false;
					try {
						jsonObject.put("message", "Vous avez été bloqué, vous ne pouvez pas jouer ce tour");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gestionParties.joueurSuivant(partieEnCours);
				}
			}

			try {
				jsonObject.put("joueur_courant", partieEnCours.getJoueur_courant().getOrdreJoueurs());
				jsonObject.put("ton_tour", ton_tour);
				jsonObject.put("etat", partieEnCours.getEtat());
				if (partieEnCours.getEtat() == Etat.FINI) {
					jsonObject.put("vainqueur", partieEnCours.getVainqueur().getPseudo());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}else{
			try {
				jsonObject.put("etat", "FINI");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
		
	}

}
