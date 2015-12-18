package be.ipl.projet_ejb.strategy;

import java.util.List;
import java.util.Random;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;

public class StrategyPrendUneCarte implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) throws JoueurNonTrouveException {
		Random random = new Random();
		if (!partie.getListeJoueurs().contains(joueur))
			throw new JoueurNonTrouveException();
		if (!partie.getListeJoueurs().contains(cible))
			throw new JoueurNonTrouveException();
		List<Carte> listeCartes = joueurPartieDao.getCartes(cible, partie);
		Carte carte = listeCartes.get(random.nextInt(listeCartes.size() + 1));
		joueurPartieDao.transfererCarte(cible, joueur, partie, carte);
	}

}
