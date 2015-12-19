package be.ipl.projet_ejb.daoimpl;

import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;
import be.ipl.projet_ejb.exceptions.PiocheVideException;
import be.ipl.projet_ejb.usecasesimpl.GestionPartiesImpl;

@LocalBean
@Stateless
public class PartieDaoImpl extends DaoImpl<Integer, Partie> {

	public PartieDaoImpl() {
		super(Partie.class);
	}

	public Partie rechercher(String nom) {
		String queryString = "select p from Partie p where p.nom = ?1";
		return recherche(queryString, nom);
	}

	public Partie creerPartie(String nom, Joueur createur) {
		return enregistrer(new Partie(nom, createur));
	}

	public boolean commencerPartie(Partie partie) {
		partie = recharger(partie.getId());
		try {
			partie = rechercher(partie.getNom());
			JoueurPartie courant = tirerJoueurAuHasard(partie);
			partie.setJoueur_courant(courant);
			partie.setStarted(true);
		} catch (Exception e) {
			System.out.println("dans commencer partie");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private JoueurPartie tirerJoueurAuHasard(Partie partie) {
		partie = recharger(partie.getId());
		List<JoueurPartie> lesJoueurs = partie.getListeJoueurs();
		Random random = new Random();
		int nb = random.nextInt(lesJoueurs.size() - 1);
		return lesJoueurs.get(nb);
	}

	public Partie passerAuJoueurSuivant(Partie partie) {

		partie = mettreAJour(partie);
		JoueurPartie suivant;

		do {
			JoueurPartie current = partie.getJoueur_courant();
			
			if (partie.isClockwise()) {
				if (current.getOrdreJoueurs() == (partie.getListeJoueurs().size())) {
					suivant = partie.getListeJoueurs().get(0);
				} else {
					suivant = partie.getListeJoueurs().get((current.getOrdreJoueurs()));
				}
			} else {
				if (current.getOrdreJoueurs() == 1) {
					suivant = partie.getListeJoueurs().get((partie.getListeJoueurs().size() - 1));
				} else {
					suivant = partie.getListeJoueurs().get((current.getOrdreJoueurs() - 2));
				}
			}
		} while (suivant.isBlocked());
		
		partie.getListeJoueurs().size();
		if (partie.getListeJoueurs().contains(suivant)) {
			partie.setJoueur_courant(suivant);
		}
		return partie;
	}

	public Partie changerSens(Partie partie) {
		partie = recharger(partie.getId());
		boolean avantSet = partie.isClockwise();
		partie.setClockwise(!avantSet);
		return partie;
	}

	public List<Partie> listerPartiesJouees(Joueur joueur) {
		String query = "SELECT p FROM JoueurPartie jp, Partie p, Joueur j " + "WHERE j = ?1 "
				+ "AND j = jp.joueur AND p = jp.partie";
		return liste(query, joueur);
	}

	public boolean ajouterJoueur(Partie partie, JoueurPartie joueurPartie) {
		try {
			partie = recharger(partie.getId());
			partie.getListeJoueurs().add(joueurPartie);
			// partie.setNbJoueur((partie.getNbJoueur()+1));
		} catch (Exception e) {
			System.out.println("fail ajouterJoueur");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Joueur afficherVainqueurPartie(Partie partie) {
		partie = recharger(partie.getId());
		String query = "SELECT p.vainqueur FROM Partie p WHERE p = ?1";
		Partie p = recherche(query, partie);
		return p.getVainqueur();
	}

	public List<JoueurPartie> listerJoueursPartie(Partie partie) {
		partie = recharger(partie.getId());
		String query = "SELECT p FROM Partie p WHERE p = ?1";
		Partie p = recherche(query, partie);
		return p.getListeJoueurs();
	}

	public void setJoueurSuivant(Partie p, JoueurPartie suivant) throws JoueurNonTrouveException {
		p = recharger(p.getId());

		if (p.getListeJoueurs().contains(suivant)) {
			p.setJoueur_courant(suivant);
		} else {
			throw new JoueurNonTrouveException();
		}
	}

	public Partie getPartieInitiale() {
		String query = "SELECT p FROM Partie p WHERE p.etat = ?1";
		Partie p = recherche(query, GestionPartiesImpl.Etat.INITIAL);
		return p;
	}

	public Partie getPartieEnCours() {
		String query = "SELECT p FROM Partie p WHERE p.etat = ?1";
		Partie p = recherche(query, GestionPartiesImpl.Etat.EN_COURS);
		if (p != null)
			p = recharger(p.getId());
		return p;
	}

	public Carte piocher(JoueurPartie joueurPartie) throws PiocheVideException {
		List<Carte> pioche = joueurPartie.getPartie().getPioche();
		if (pioche.size() == 0) {
			throw new PiocheVideException("Il n'y a plus de cartes dans la pioche");
		}

		java.util.Collections.shuffle(pioche);
		Carte c = pioche.remove(0);
		return c;
	}

}
