package be.ipl.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.ipl.projet_ejb.usecases.GestionJoueurs;
import be.ipl.projet_ejb.usecasesimpl.GestionJoueursImpl;

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

		String pseudo = request.getParameter("form-username");
		String mdp = request.getParameter("form-password");

		System.out.println(pseudo + " " + mdp);
		
		getServletContext().getNamedDispatcher("attente.html").forward(request, response);

	}

}
