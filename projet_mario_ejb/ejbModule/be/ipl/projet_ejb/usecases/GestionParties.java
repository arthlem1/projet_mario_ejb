package be.ipl.projet_ejb.usecases;

import java.util.List;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

public interface GestionParties {

	/**
	 * Cr�ation de la partie. La date et l'heure de cr�ation sont enregistr�s.
	 * @param nom
	 * @param joueur
	 */
	void creerPartie(String nom, Joueur joueur);
	/**
	 * recherche de la partie avec son nom. Si elle n'existe pas,
	 * la m�thode renvoie null.
	 * @param nom
	 * @return Partie
	 */
	Partie rechercherPartie(String nom);
	
	/**
	 * Ajouter le joueur dans la partie courante.
	 * @param joueur
	 * @return True si le joueur a pu �tre ajout�, false sinon.
	 */
	boolean ajouterJoueur(Joueur joueur);
	
	/**
	 * Lancer la partie. Le joueur qui commence est tir� au hasard.
	 * @param nom
	 * @return Partie
	 */
	Partie commencerPartie(String nom);
	
	/**
	 * Passer au joueur suivant.
	 * @param partie
	 * @param suivant
	 * @return Partie MAJ
	 */
	Partie joueurSuivant(Partie partie, JoueurPartie suivant);
	
	/**
	 * Changer le sens du tour de la partie
	 * @param partie
	 */
	void changerSens(Partie partie);
	
	/**
	 * Lister l'ensemble des parties jouees par le joueur.
	 * @param joueur
	 * @return
	 */
	List<Partie> listerPartiesJouees(Joueur joueur);
}
