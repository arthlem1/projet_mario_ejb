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

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionJoueurs;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.html")
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

		String prenom = "";//TODO récupérer ce champ!
		String pseudo = request.getParameter("form-username");
		String mdp = request.getParameter("form-password");

		Joueur joueur = gestionJoueurs.creerJoueur(prenom,pseudo, mdp);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				System.out.println(joueur.getPseudo());
			}
		};
		timer.schedule(task, 30000);

		getServletContext().getNamedDispatcher("attente.html").forward(request, response);

	}

}