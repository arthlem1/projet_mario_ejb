package be.ipl.servlets;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="FiltreControleur", urlPatterns = "/restreint/*", dispatcherTypes = {DispatcherType.REQUEST} )
public class FiltreControleur implements Filter {
	private FilterConfig fConfig;
	
	private static final String ACCES_CONNEXION = "/Login";
	private static final String SESSION = "joueur";
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (! (request instanceof HttpServletRequest)) {
			String message = "Ce Site n'est accessible qu'en HTTP";
			request.setAttribute("message", message);
			request.getServletContext().getNamedDispatcher("ErreurParam").forward(request, response);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		if (!req.isRequestedSessionIdFromCookie()) {
			String message = "Pour le bon fonctionnement de ce site, vous devez accepter les cookies.<br> Si vous ne les accetez pas vous ne pourrez pas d�passer la page d'accueil.";
			request.setAttribute("messageAcceuil", message);
			request.getServletContext().getNamedDispatcher("index.html").forward(request, response);
			return;
		} else
			request.removeAttribute("messageAccueil");
		/*NE PAS TOUCHER A LA PARTIE DU DESSUS*/
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if(session.getAttribute(SESSION) == null){
			req.getRequestDispatcher(ACCES_CONNEXION).forward(request, response);
		}else
			chain.doFilter(request, response);
	}

}
