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

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.PiocheVideException;
import be.ipl.projet_ejb.usecases.GestionCartes;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class TirerCarte
 */
@WebServlet("/TirerCarte")
public class TirerCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionParties gestionParties;
	@EJB
	private GestionCartes gestionCartes;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TirerCarte() {
		super();
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

		JSONObject resultat = new JSONObject();

		try {
			partieEnCours = gestionJoueurPartie.tirerUneCarte(partieEnCours, joueur);

			JSONObject carte = new JSONObject();

			try {
				resultat.put("success", "1");
				Carte tmpCarte = partieEnCours.getJoueur_courant().getMainsCarte()
						.get(partieEnCours.getJoueur_courant().getMainsCarte().size() - 1);
				carte.put("id", tmpCarte.getId());
				carte.put("interaction", gestionJoueurPartie.besoinCible(tmpCarte));
				carte.put("codeEffet", tmpCarte.getCodeEffet());
				carte.put("description", gestionCartes.descriptionCarte(tmpCarte.getCodeEffet()));
				carte.put("cout", tmpCarte.getCout());
				resultat.put("carte", carte);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (PiocheVideException e) {
			try {
				resultat.put("success", "0").put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}

		response.setContentType("application/json");
		response.getWriter().write(resultat.toString());
	}

}
