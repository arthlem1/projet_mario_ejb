package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.util.Util;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;

@Singleton
@Startup
public class GestionPartiesImpl implements GestionParties {

	@EJB PartieDaoImpl partieDao;
	
	@Override
	public void creerPartie(String nom, Joueur createur) {
		Util.checkString(nom);
		Util.checkObject(createur);
		partieDao.creerPartie(nom, createur);
	}

	@Override
	public Partie rechercherPartie(String nom) {
		Util.checkString(nom);
		return partieDao.rechercher(nom);
	}

	@Override
	public boolean ajouterJoueur(Joueur joueur) {
		Util.checkObject(joueur);
		return false;
	}

	@Override
	public Partie commencerPartie(String nom) {
		Util.checkString(nom);
		return partieDao.commencerPartie(nom);
	}

	@Override
	public Partie joueurSuivant(Partie partie, JoueurPartie suivant) {
		Util.checkObject(suivant);
		Util.checkObject(partie);
		return partieDao.passerAuJoueurSuivant(partie, suivant);
	}

	@Override
	public void changerSens(Partie partie) {
		Util.checkObject(partie);
		partieDao.changerSens(partie);
	}

	@Override
	public List<Partie> listerPartiesJouees(Joueur joueur) {
		Util.checkObject(joueur);
		return partieDao.listerPartiesJouees(joueur);
	}
	
}
