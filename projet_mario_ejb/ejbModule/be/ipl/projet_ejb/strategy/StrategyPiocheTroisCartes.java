package be.ipl.projet_ejb.strategy;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;

public class StrategyPiocheTroisCartes implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) {
		Carte carte = partie.getPioche().remove(0);
		joueurPartieDao.rajouterCarte(joueur, partie, carte);
	}

}
