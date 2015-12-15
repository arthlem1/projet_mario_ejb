package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionJoueurs;
import be.ipl.projet_ejb.util.Util;
@Singleton
@Startup
public class GestionJoueursImpl implements GestionJoueurs{

	@EJB 
	private JoueurDaoImpl joueurDao;
	
	@Override
	public Joueur creerJoueur(String prenom, String pseudo, String mdp) {
		Util.checkString(pseudo);
		Util.checkString(mdp);
		Util.checkString(prenom);
		Joueur joueur = joueurDao.rechercher(pseudo);
		if (joueur == null) {
			joueur = new Joueur(prenom,pseudo, mdp);
			joueur = joueurDao.enregistrer(joueur);
		}
		return joueur;
	}

	@Override
	public Joueur rechercher(String pseudo) {
		Util.checkString(pseudo);
		return joueurDao.rechercher(pseudo);
	}

	
}
