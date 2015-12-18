package be.ipl.projet_ejb.strategy;

import java.util.List;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;

public class StrategyUneCarteRestante implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) throws JoueurNonTrouveException {
//		if (!partie.getListeJoueurs().contains(cible))
//			throw new JoueurNonTrouveException();
		List<Carte> liste = joueurPartieDao.getCartes(cible, partie);
		while (liste.size() > 1) {
			partie.getPioche().add(liste.remove(0));
		}
	}
}
