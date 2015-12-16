package be.ipl.projet_ejb.strategy;

import java.util.HashMap;
import java.util.Map;

public abstract class Strategy {

	private Map<Integer, Strategy> strategies = new HashMap<Integer,Strategy>();
	
	public Strategy() {
		initialiser();
	}
	
	private Map<Integer, Strategy> initialiser(){
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
	
	public void effectuer(int codeEffet){
		strategies.get(codeEffet).traitement();
	}
	
	protected abstract void traitement();
}
