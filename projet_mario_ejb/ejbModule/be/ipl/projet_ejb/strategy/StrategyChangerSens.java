package be.ipl.projet_ejb.strategy;

import java.util.List;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

public class StrategyChangerSens implements Strategy {

	@Override
	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible, boolean clockwize) {
		partieDao.changerSens(partie);
		List<JoueurPartie> joueurs= partie.getListeJoueurs();
		int[] nbDeBase=new int[joueurs.size()];
		for (JoueurPartie j : partie.getListeJoueurs()) {
			nbDeBase[j.getOrdreJoueurs()-1]=j.getMainsDe().size();
		}
		if(clockwize){
			for (int i = 0; i < joueurs.size()-2; i++) {
				joueurPartieDao.transfererDe(joueurs.get(i).getJoueur(), joueurs.get(i+1).getJoueur(), nbDeBase[i], partie);
			}
			joueurPartieDao.transfererDe(joueurs.get(joueurs.size()-1).getJoueur(), joueurs.get(0).getJoueur(), nbDeBase[joueurs.size()-1], partie);
		}else{
			
			for (int i = 0; i < joueurs.size()-2; i++) {
				joueurPartieDao.transfererDe(joueurs.get(i+1).getJoueur(),joueurs.get(i).getJoueur(),nbDeBase[i], partie);
			}
			joueurPartieDao.transfererDe(joueurs.get(0).getJoueur(),joueurs.get(joueurs.size()-1).getJoueur(),nbDeBase[joueurs.size()-1], partie);
		}
	}

}
