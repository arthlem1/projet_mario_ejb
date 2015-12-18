package be.ipl.servlets;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
import be.ipl.projet_ejb.exceptions.PartieDejaEnCoursException;
import be.ipl.projet_ejb.exceptions.PasAssezDeJoueursException;
import be.ipl.projet_ejb.exceptions.PiocheVideException;
import be.ipl.projet_ejb.usecases.GestionParties;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int TEMPS = 30000;

	@EJB
	private GestionParties gestionPartie;
	private Partie partie = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Create() {
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
		String nom = request.getParameter("nom");

		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute("joueur");

		JSONObject jsonObject = new JSONObject();

		

		try {
			partie = gestionPartie.creerPartie(nom, joueur);
			try {
				jsonObject.put("success", "1");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			session.setAttribute("partie", partie);

			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					
					try {
						gestionPartie.commencerPartie(partie);
						partie = gestionPartie.initialiserPioche(partie);
						try {
							partie = gestionPartie.initialiserMainsCartes(partie);
						} catch (PiocheVideException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						partie = gestionPartie.initialiserMainsDes(partie);
					} catch (PasAssezDeJoueursException e) {
						
						System.out.println("pas assez de joueurs");
					}
					
				}
			};
			timer.schedule(task, TEMPS);

		} catch (PartieDejaEnCoursException | MaxJoueursException e) {
			try {
				jsonObject.put("success", "0").put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());

	}

}
