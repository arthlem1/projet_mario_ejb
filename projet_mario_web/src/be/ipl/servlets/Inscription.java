package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionJoueurs;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscrire.html")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
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
		String prenom = request.getParameter("form-first-name");//prendre en compte le nom
		String pseudo = request.getParameter("form-username");
		String mdp = request.getParameter("form-password");
		Joueur joueur = gestionJoueurs.rechercher(pseudo);
		if(joueur!=null){
			String message = "Ce pseudo est déjà utilisé. Veuillez en utiliser un autre S.V.P.";
			System.out.println(message);
			getServletContext().getNamedDispatcher("index.html").forward(request, response);
			return;
		}
		gestionJoueurs.creerJoueur(pseudo, mdp);
		getServletContext().getNamedDispatcher("attente.html").forward(request, response);
	}
	

}
