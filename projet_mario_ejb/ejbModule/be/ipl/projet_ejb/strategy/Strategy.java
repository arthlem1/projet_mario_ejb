package be.ipl.projet_ejb.strategy;

import java.util.HashMap;
import java.util.Map;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;

public interface Strategy {

	static Map<Integer, Strategy> strategies = initialiser();

	static Map<Integer, Strategy> initialiser() {
		Map<Integer, Strategy> strategies = new HashMap<Integer, Strategy>();
		Strategy supprUnDe = new StrategySupprUnDe();
		Strategy changerSens = new StrategyChangerSens();
		Strategy supprDeuxDe = new StrategySupprDeuxDe();
		Strategy donUnDe = new StrategyDonUnDe();
		Strategy prendUneCarte = new StrategyPrendUneCarte();
		Strategy uneCarteRestante = new StrategyUneCarteRestante();
		Strategy piocheTroisCartes = new StrategyPiocheTroisCartes();
		Strategy tousDeuxCartesSaufVous = new StrategyTousDeuxCartesSaufVous();
		Strategy skip = new StrategySkip();
		Strategy rejoueEtChangeSens = new StrategyRejoueEtChangeSens();

		strategies.put(1, supprUnDe);
		strategies.put(2, changerSens);
		strategies.put(3, supprDeuxDe);
		strategies.put(4, donUnDe);
		strategies.put(5, prendUneCarte);
		strategies.put(6, uneCarteRestante);
		strategies.put(7, piocheTroisCartes);
		strategies.put(8, tousDeuxCartesSaufVous);
		strategies.put(9, skip);
		strategies.put(10, rejoueEtChangeSens);
		return strategies;
	}

	public void effectuer(DeDaoImpl deDao, PartieDaoImpl partieDao, JoueurPartieDaoImpl joueurPartieDao, Partie partie,
			Joueur joueur, Joueur cible) throws JoueurNonTrouveException;

}
