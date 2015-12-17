package be.ipl.projet_ejb.daoimpl;

import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;
import be.ipl.projet_ejb.usecasesimpl.GestionPartiesImpl;

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
	
	public Partie passerAuJoueurSuivant(Partie partie){
		JoueurPartie suivant;
		JoueurPartie current=partie.getJoueur_courant();
		if(partie.isClockwise()){
			if(current.getOrdreJoueurs()==(partie.getListeJoueurs().size()+1)){
				suivant=partie.getListeJoueurs().get(0);
			}else{
				suivant=partie.getListeJoueurs().get((current.getOrdreJoueurs()+1));
			}
		}else{
			if(current.getOrdreJoueurs()==1){
				suivant=partie.getListeJoueurs().get((partie.getListeJoueurs().size()+1));
			}else{
				suivant=partie.getListeJoueurs().get((current.getOrdreJoueurs()-1));
			}
		}
		Partie p = getPartieInitiale();
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
				+ "AND j.id = jp.joueur AND p.id = jp.partie";
		return liste(query, joueur.getId());
	}

	public boolean ajouterJoueur(Partie partie, JoueurPartie joueurPartie) {
		try {
			partie = recharger(partie.getNom());
			partie.getListeJoueurs().add(joueurPartie);
			//partie.setNbJoueur((partie.getNbJoueur()+1));
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
	
	public void setJoueurSuivant(Partie p, JoueurPartie suivant) throws JoueurNonTrouveException {
		if(p.getListeJoueurs().contains(suivant)){
			p.setJoueur_courant(suivant);
		}else{
			throw new JoueurNonTrouveException();
		}
		mettreAJour(p);		
	}	
	
	public Partie getPartieInitiale(){
		String query = "SELECT p FROM Partie p WHERE p.etat = ?1";
		Partie p = recherche(query, GestionPartiesImpl.Etat.INITIAL);
		return p;
	}
	
	public Partie getPartieEnCours(){
		String query = "SELECT p FROM Partie p WHERE p.etat = ?1";
		Partie p = recherche(query, GestionPartiesImpl.Etat.EN_COURS);
		return p;
	}
}
