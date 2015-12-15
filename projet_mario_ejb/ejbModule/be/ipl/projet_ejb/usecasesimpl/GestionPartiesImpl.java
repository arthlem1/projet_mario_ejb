package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;

@Singleton
@Startup
public class GestionPartiesImpl implements GestionParties {

	@EJB PartieDaoImpl partieDao;
	
	@Override
	public void creerPartie(String nom) {
		//TODO créer une nouvelle partie
		
	}

	@Override
	public Partie rechercherPartie(String nom) {
		// TODO rechercher une partie
		return null;
	}

	@Override
	public boolean ajouterJoueur(Joueur joueur) {
		// TODO ajouter joueur dans la partie
		return false;
	}

}
