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
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.MaxJoueursException;
import be.ipl.projet_ejb.exceptions.PartieDejaEnCoursException;



/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionParties gestionPartie;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
		JsonBuilderFactory factory = Json.createBuilderFactory(config);
		JsonObject value;
		
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute("joueur");
		
		Partie partie = null;
		
		try {
			partie = gestionPartie.creerPartie(nom, joueur);
			value = factory.createObjectBuilder().add("success", "1").build();
			session.setAttribute("partie", partie);
		} catch (PartieDejaEnCoursException e) {
			value = factory.createObjectBuilder().add("success", "0").add("message", e.getMessage()).build();
		} catch (MaxJoueursException e) {
			value = factory.createObjectBuilder().add("success", "0").add("message", e.getMessage()).build();
		}
		
		
//		if((partie = gestionPartie.creerPartie(nom, joueur)) != null){
//			session.setAttribute("partie", partie);
//			
//			value = factory.createObjectBuilder().add("success", "1").build();
//		}else{
//			value = factory.createObjectBuilder().add("success", "0").add("message", "Une partie est déjà en cours...").build();
//		}
		
		response.setContentType("application/json");
		response.getWriter().write(value.toString());
		
		
	}

}
