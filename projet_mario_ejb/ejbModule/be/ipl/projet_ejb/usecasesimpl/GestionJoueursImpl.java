package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionJoueurs;
@Singleton
@Startup
public class GestionJoueursImpl implements GestionJoueurs{

	@EJB 
	private JoueurDaoImpl joueurDao;
	
	@Override
	public Joueur creerJoueur(String pseudo, String mdp) {
		Joueur joueur = joueurDao.rechercher(pseudo);
		if (joueur == null) {
			joueur = new Joueur(pseudo, mdp);
			joueur = joueurDao.enregistrer(joueur);
		}
		return joueur;
	}

	
}
