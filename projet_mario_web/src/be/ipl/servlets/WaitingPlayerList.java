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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class WaitingPlayerList
 */
@WebServlet("/WaitingPlayerList")
public class WaitingPlayerList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionParties gestionPartie;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaitingPlayerList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		Partie partie = (Partie) session.getAttribute("partie");

		List<Joueur> listeJoueurs = gestionPartie.listeJoueursPartie(partie);

		JSONArray jsonArray = new JSONArray();

		JSONArray listeJoueursJson = new JSONArray();

		JSONObject etatPartie = new JSONObject();

		try {
			Partie p = gestionPartie.getPartieInitiale();
			if (p == null) {
				p = gestionPartie.getPartieEnCours();

				if (p == null) {
					etatPartie.put("etat", "FINI");
				} else {
					etatPartie.put("etat", "EN COURS");
					
				}
			} else {
				etatPartie.put("etat", p.getEtat());
			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listeJoueurs.forEach(joueur -> {
			JSONObject part = new JSONObject();
			try {
				part.put("prenom", joueur.getPrenom()).put("pseudo", joueur.getPseudo());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listeJoueursJson.put(part);
		});

		jsonArray.put(etatPartie);
		jsonArray.put(listeJoueursJson);

		response.setContentType("application/json");
		System.out.println(jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

}
