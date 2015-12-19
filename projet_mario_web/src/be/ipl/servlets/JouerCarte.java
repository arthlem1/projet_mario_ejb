package be.ipl.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;
import be.ipl.projet_ejb.usecases.GestionCartes;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionJoueurs;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class JouerCarte
 */
@WebServlet("/JouerCarte")
public class JouerCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionParties gestionParties;
	@EJB
	private GestionCartes gestionCartes;
	@EJB
	private GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JouerCarte() {
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

		int id_carte = Integer.parseInt(request.getParameter("id_carte"));

		Carte carte = gestionCartes.rechercherCarte(id_carte);

		Partie partieEnCours = gestionParties.getPartieEnCours();

		List<Carte> listeCarte = gestionJoueurPartie.listerCartes(joueur, partieEnCours);

		System.out.println(id_carte);

		int nb_w = (int) session.getAttribute("nb_w");

		JSONObject resultat = new JSONObject();

		if (listeCarte.contains(carte)) {
			List<Carte> listeCarteUtilisables = gestionJoueurPartie.listerCartesUtilisables(nb_w,
					partieEnCours);
			if (listeCarteUtilisables.contains(carte)) {

				Joueur cible = joueur;
				if (gestionJoueurPartie.besoinCible(carte)) {
					String pseudo = request.getParameter("pseudo");
					cible = gestionJoueurs.rechercher(pseudo);
				}
				try {
					gestionJoueurPartie.utiliserCarte(carte, partieEnCours, joueur, cible, partieEnCours.isClockwise());
				} catch (JoueurNonTrouveException e1) {
					e1.printStackTrace();
				}
				try {
					resultat.put("success", "1");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultat.put("success", "0").put("message", "Vous n'avez pas assez de pièces pour faire ça");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				resultat.put("success", "0").put("message", "Vous n'avez pas cette carte");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		session.removeAttribute("nb_w");

		response.setContentType("application/json");
		response.getWriter().write(resultat.toString());

	}

}
