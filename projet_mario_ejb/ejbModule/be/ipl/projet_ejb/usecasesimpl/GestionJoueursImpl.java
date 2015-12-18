package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.exceptions.JoueurDejaExistantException;
import be.ipl.projet_ejb.usecases.GestionJoueurs;
import be.ipl.projet_ejb.util.Util;

@Stateless
@Startup
public class GestionJoueursImpl implements GestionJoueurs {

	@EJB
	private JoueurDaoImpl joueurDao;

	@Override
	public Joueur creerJoueur(String prenom, String pseudo, String mdp) throws JoueurDejaExistantException {
		Util.checkString(pseudo);
		Util.checkString(mdp);
		mdp = Util.getMD5(mdp);
		Util.checkString(prenom);
		Joueur joueur = joueurDao.rechercher(pseudo);
		if(joueur!=null)
			throw new JoueurDejaExistantException("Ce pseudo est déjà pris!");
		joueur = new Joueur(prenom, pseudo, mdp);
		return joueurDao.enregistrer(joueur);
	}

	@Override
	public Joueur rechercher(String pseudo) {
		Util.checkString(pseudo);
		return joueurDao.rechercher(pseudo);
	}

	@Override
	public Joueur login(String pseudo, String mdp) {
		Util.checkString(pseudo);
		Util.checkString(mdp);
		return joueurDao.login(pseudo, mdp);
	}

}
