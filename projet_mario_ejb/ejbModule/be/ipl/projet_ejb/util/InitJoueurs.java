package be.ipl.projet_ejb.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;

@Singleton
@Startup
public class InitJoueurs {

	@EJB
	JoueurDaoImpl joueurdao;
	
	@PostConstruct
	public void init() {
		String mdp = Util.getMD5("em");
		joueurdao.enregistrer(new Joueur("em", "em", mdp));
		mdp= Util.getMD5("mi");
		joueurdao.enregistrer(new Joueur("mi", "mi", mdp));
		mdp= Util.getMD5("ol");
		joueurdao.enregistrer(new Joueur("ol", "ol", mdp));
		System.out.println("j'ai enregistre les joueurs");
	}
}
