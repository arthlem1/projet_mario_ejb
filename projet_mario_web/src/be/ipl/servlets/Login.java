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

import org.json.JSONException;
import org.json.JSONObject;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionJoueurs;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
		JsonBuilderFactory factory = Json.createBuilderFactory(config);
		JsonObject value = null;
		JSONObject jsonObject = new JSONObject();
		
		Joueur joueur = gestionJoueurs.login(pseudo, mdp);
		if(joueur != null){
			HttpSession session = request.getSession();
			session.setAttribute("joueur", joueur);
			
			System.out.println(joueur.getPseudo());
			try {
				jsonObject.put("success", "1");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//value = factory.createObjectBuilder().add("success", "1").add("message", "Connexion avec succès").build();
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
			return;
		}else{
			try {
				jsonObject.put("success", "0");
				jsonObject.put("message", "Vérifiez vos informations.");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//value = factory.createObjectBuilder().add("success", "0").add("message", "Vérifiez vos informations.").build();
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
		


	}

}