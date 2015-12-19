package be.ipl.servlets;

import java.io.IOException;
import java.util.Map;

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
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class Lancer_des
 */
@WebServlet("/Lancer_des")
public class Lancer_des extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurPartie gestionJoueurPartie;
	@EJB
	private GestionParties gestionParties;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Lancer_des() {
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

		int ordre = gestionJoueurPartie.ordreJoueur(joueur.getId(), partieEnCours.getId());

		int nbDes = partieEnCours.getListeJoueurs().get(ordre-1).getMainsDe().size();

		Map<String, Integer> map = gestionJoueurPartie.lancerDes(joueur, partieEnCours, nbDes);
		
		session.setAttribute("nb_w", map.get("w"));

		JSONObject resultat = new JSONObject();
		
		System.out.println("passage de la lancer dés");

		try {
			resultat.put("nb_w", map.get("w"));
			resultat.put("nb_d", map.get("d"));
			resultat.put("nb_c", map.get("c"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(resultat.toString());
	}

}
