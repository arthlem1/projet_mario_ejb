package be.ipl.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.exceptions.JoueurDejaExistantException;
import be.ipl.projet_ejb.usecases.GestionJoueurs;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
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
		String prenom = request.getParameter("prenom");
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");


		Map<String, Object> config = new HashMap<String, Object>();
		config.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
		JsonBuilderFactory factory = Json.createBuilderFactory(config);
		JsonObject value;


		try {
			gestionJoueurs.creerJoueur(prenom, pseudo, mdp);
		} catch (JoueurDejaExistantException e) {
			value = factory.createObjectBuilder().add("success", "0").add("message", e.getMessage()).build();
			response.setContentType("application/json");
			response.getWriter().write(value.toString());
			return;
		}

		HttpSession session = request.getSession();
		Joueur nouveauJoueur = new Joueur(prenom, pseudo, mdp);

		session.setAttribute("joueur", nouveauJoueur);

		value = factory.createObjectBuilder().add("success", "1").add("message", "Inscription avec succès!").build();
		response.setContentType("application/json");
		response.getWriter().write(value.toString());

	}

}
