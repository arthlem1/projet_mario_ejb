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
 * Servlet implementation class ListerPartiesJouee
 */
@WebServlet("/ListerPartiesJouees")
public class ListerPartiesJouees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionParties gestionPartie;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListerPartiesJouees() {
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

		List<Partie> parties = gestionPartie.listerPartiesJouees(joueur);

		JSONArray jsonArray = new JSONArray();

		if (parties != null) {

			parties.forEach(partie -> {
				JSONObject jsonObject = new JSONObject();
				String pseudo = "Néant";
				if (partie.getVainqueur() != null) {
					pseudo = partie.getVainqueur().getPseudo();
				}
				int nb = 0;
				if (partie.getListeJoueurs() != null) {
					nb = partie.getListeJoueurs().size();
				}

				try {
					jsonObject.put("nom", partie.getNom());
					jsonObject.put("vainqueur", pseudo);
					jsonObject.put("nb_joueur", nb);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(jsonObject.toString());
				jsonArray.put(jsonObject);

			});
			response.setContentType("application/json");
			response.getWriter().write(jsonArray.toString());
			return;
		} else {
			JSONObject reponse = new JSONObject();
			try {
				reponse.put("message", "Aucun résultat");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.setContentType("application/json");
			response.getWriter().write(reponse.toString());

		}

	}

}
