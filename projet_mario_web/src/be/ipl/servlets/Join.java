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
import be.ipl.projet_ejb.exceptions.MaxJoueursException;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.usecasesimpl.GestionPartiesImpl.Etat;

/**
 * Servlet implementation class Join
 */
@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionParties gestionParties;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Join() {
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

		JSONObject jsonObject = new JSONObject();

		Partie partie = gestionParties.getPartieInitiale();

		if (partie != null) {

			try {
				if (gestionParties.ajouterJoueur(partie, joueur)) {
					try {
						session.setAttribute("partie", partie);
						jsonObject.put("success", "1");
						partie.setEtat(Etat.EN_COURS);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					try {
						jsonObject.put("success", "0");
						jsonObject.put("message", "Impossible de rejoindre la partie");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} catch (MaxJoueursException e) {

				try {
					jsonObject.put("success", "0");
					jsonObject.put("message", e.getMessage());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		} else {
			try {
				jsonObject.put("success", "2");
				jsonObject.put("message", "Aucune partie en cours.");
			} catch (JSONException e) {

				e.printStackTrace();
			}

		}

		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());

	}

}
