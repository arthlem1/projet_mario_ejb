package be.ipl.projet_ejb.strategy;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.domaine.JoueurPartie;

public class StrategyRejoueEtChangeSens implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) {
		JoueurPartie jp= joueurPartieDao.getPlayer(joueur.getId(), partie.getId());
		partieDao.passerAuJoueurSuivant(partie, jp);
		partieDao.changerSens(partie);
	}


}
