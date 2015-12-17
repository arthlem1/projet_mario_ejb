package be.ipl.projet_ejb.strategy;

import java.util.List;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

public class StrategyTousDeuxCartesSaufVous implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) {
		List<JoueurPartie> liste = partie.getListeJoueurs();
		for (JoueurPartie joueurPartie : liste) {
			if(joueurPartie.getJoueur().getId()!=joueur.getId()){
				List<Carte> cartesDuJoueur = joueurPartie.getMainsCarte();
				while(cartesDuJoueur.size() > 2){
					partie.getPioche().add(cartesDuJoueur.remove(0));
				}
			}
		}
	}
}
