package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionCartes;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class Init_partie
 */
@WebServlet("/Init_partie")
public class Init_partie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionParties gestionParties;
	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionCartes gestionCartes;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Init_partie() {
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

		JSONObject partie = new JSONObject();

		JSONArray mes_cartes = new JSONArray();

		try {
			partie.put("pioche", partieEnCours.getPioche().size());
			partie.put("nb_joueur", partieEnCours.getListeJoueurs().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		int ordre = gestionJoueurPartie.ordreJoueur(joueur.getId(), partieEnCours.getId());

		partieEnCours.getListeJoueurs().get(ordre - 1).getMainsCarte().forEach(carte -> {
			JSONObject card = new JSONObject();
			try {
				card.put("id", carte.getId());
				card.put("codeEffet", gestionCartes.descriptionCarte(carte.getCodeEffet()));
				card.put("cout", carte.getCout());
			} catch (Exception e) {
				e.printStackTrace();
			}
			mes_cartes.put(card);
		});

		JSONArray nombreCarteAutresJoueurs = new JSONArray();

		partieEnCours.getListeJoueurs().forEach(joueurpartie -> {
			if (joueurpartie.getJoueur().getId() != joueur.getId()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("nb_cartes", joueurpartie.getMainsCarte().size());
					nombreCarteAutresJoueurs.put(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		try {
			partie.put("nb_des", gestionJoueurPartie.nbDe(joueur, partieEnCours));
			partie.put("mes_cartes", mes_cartes);
			partie.put("nb_cartes_autres", nombreCarteAutresJoueurs);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.getWriter().write(partie.toString());

	}

}
