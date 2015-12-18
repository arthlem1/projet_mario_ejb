package be.ipl.projet_ejb.strategy;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;

public class StrategySupprDeuxDe implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible, boolean clockwize) throws JoueurNonTrouveException {
//		if (!partie.getListeJoueurs().contains(joueur))
//			throw new JoueurNonTrouveException();
		joueurPartieDao.retirerDe(joueur.getId(), partie);
		joueurPartieDao.retirerDe(joueur.getId(), partie);
	}

}
