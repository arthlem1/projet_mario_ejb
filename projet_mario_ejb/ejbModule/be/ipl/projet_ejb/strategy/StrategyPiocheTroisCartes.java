package be.ipl.projet_ejb.strategy;

import java.util.List;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;

public class StrategyPiocheTroisCartes implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) throws JoueurNonTrouveException {
		List<Carte> pioche = partie.getPioche();
		if (!partie.getListeJoueurs().contains(joueur))
			throw new JoueurNonTrouveException();
		for (int i = 0; i < 3; i++) {
			if (pioche.size() == 0) {
				/*
				 * TODO à discuter. Selon les consignes, si plus de carte dans
				 * la pioche, prendre une carte dans la main d'un autre
				 */
			}
			Carte carte = pioche.remove(0);// premiere carte
			JoueurPartie joueurPartie = joueurPartieDao.getPlayer(joueur.getId(), partie.getId());
			joueurPartieDao.rajouterCarte(joueurPartie, carte);
		}
	}

}
