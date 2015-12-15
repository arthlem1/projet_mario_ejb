package be.ipl.projet_ejb.daoimpl;

import java.util.List;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.domaine.JoueurPartie;

public class PartieDaoImpl extends DaoImpl<String, Partie> {
	//TODO tirerJoueurAuHasard Laeti
	//TODO commencerPartie Laeti
	
	private static final long serialVersionUID = 1L;
	
	public PartieDaoImpl() {
		super(Partie.class);
	}
	
	Partie passerAuJoueurSuivant(Partie partie, JoueurPartie suivant){
		String query = "SELECT p FROM Partie p "
				+ "WHERE p.joueur_id = ?1";
		Partie p = recherche(query, suivant.getJoueurId());
		if(p.getListeJoueurs().contains(suivant)){
			p.setJoueur_courant(suivant);
		}
		return mettreAJour(p);
	}

	Partie changerSens(Partie partie){
		partie.setClockwise(false);
		return mettreAJour(partie);
	}

	List<Partie> listerPartiesJouees(Joueur joueur){
		String query = "SELECT p FROM JoueurPartie jp, Partie p, Joueur j "
				+ "WHERE j.id = ?1 "
				+ "AND j.id = jp.id AND p.id = jp.id";
		return liste(query, joueur.getId());
	}
}
