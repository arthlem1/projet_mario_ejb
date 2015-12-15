package be.ipl.projet_ejb.daoimpl;

import java.util.List;
import java.util.Random;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

public class PartieDaoImpl extends DaoImpl<String, Partie> {
	//TODO tirerJoueurAuHasard Laeti
	//TODO commencerPartie Laeti
	
	private static final long serialVersionUID = 1L;
	
	public PartieDaoImpl(Class<Partie> entityClass) {
		super(entityClass);
	}

	public Partie rechercher(String nom) {
		String queryString = "select p from Partie p where p.nom = ?1";
		return recherche(queryString, nom);
	}

	public Partie creerPartie(String nom, Joueur createur) {
		return enregistrer(new Partie(nom, createur));
	}

	public Partie commencerPartie(String nom) {
		Partie partie = rechercher(nom);
		JoueurPartie courant = tirerJoueurAuHasard(partie);
		partie.setJoueur_courant(courant);
		partie.setStarted(true);
		return mettreAJour(partie);
	}

	public JoueurPartie tirerJoueurAuHasard(Partie partie) {
		List<JoueurPartie> lesJoueurs = partie.getListeJoueurs();
		Random random = new Random();
		int nb = random.nextInt(lesJoueurs.size()) + 1;
		return lesJoueurs.get(nb);
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
