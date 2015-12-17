package be.ipl.projet_ejb.daoimpl;

import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

@LocalBean
@Stateless
public class PartieDaoImpl extends DaoImpl<String, Partie> {
	
	public PartieDaoImpl(){
		super(Partie.class);
	}
	
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

	public boolean commencerPartie(Partie partie) {
		try {
			partie = rechercher(partie.getNom());
			JoueurPartie courant = tirerJoueurAuHasard(partie);
			partie.setJoueur_courant(courant);
			partie.setStarted(true);
			mettreAJour(partie);
		} catch (Exception e) {
			return false;
		}
		return true;		
	}

	private JoueurPartie tirerJoueurAuHasard(Partie partie) {
		List<JoueurPartie> lesJoueurs = partie.getListeJoueurs();
		Random random = new Random();
		int nb = random.nextInt(lesJoueurs.size()) + 1;
		
		return lesJoueurs.get(nb);
	}
	
	public Partie passerAuJoueurSuivant(Partie partie, JoueurPartie suivant){
		String query = "SELECT p FROM Partie p "
				+ "WHERE p.joueur_id = ?1";
		Partie p = recherche(query, suivant.getJoueurId());
		if(p.getListeJoueurs().contains(suivant)){
			p.setJoueur_courant(suivant);
		}
		return mettreAJour(p);
	}

	public Partie changerSens(Partie partie){
		boolean avantSet = partie.isClockwise();
		partie.setClockwise(!avantSet);
		return mettreAJour(partie);
	}

	public List<Partie> listerPartiesJouees(Joueur joueur){
		String query = "SELECT p FROM JoueurPartie jp, Partie p, Joueur j "
				+ "WHERE j.id = ?1 "
				+ "AND j.id = jp.joueurPartiePK.joueur_id AND p.id = jp.joueurPartiePK.partie_id";
		return liste(query, joueur.getId());
	}

	public boolean ajouterJoueur(Partie partie, JoueurPartie joueurPartie) {
		try {
			partie.getListeJoueurs().add(joueurPartie);
			mettreAJour(partie);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Joueur afficherVainqueurPartie(Partie partie){
		String query = "SELECT p.vainqueur FROM Partie p WHERE p.id = ?1";
		Partie p=recherche(query, partie.getId());
		return p.getVainqueur(); 
	}
	
	public List<JoueurPartie> listerJoueursPartie(Partie partie){
		String query = "SELECT p FROM Partie p WHERE p.id = ?1";
		Partie p = recherche(query, partie.getId());
		return p.getListeJoueurs(); 
	}		
}
