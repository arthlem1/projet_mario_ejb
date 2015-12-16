package be.ipl.projet_ejb.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

@Remote
public interface GestionParties {

	/**
	 * Création de la partie. La date et l'heure de création sont enregistrés.
	 * @param nom
	 * @param joueur
	 * @return 
	 */
	Partie creerPartie(String nom, Joueur joueur);
	/**
	 * recherche de la partie avec son nom. Si elle n'existe pas,
	 * la méthode renvoie null.
	 * @param nom
	 * @return Partie
	 */
	Partie rechercherPartie(String nom);
	
	/**
	 * Ajouter le joueur dans la partie courante.
	 * @param joueur
	 * @return True si le joueur a pu être ajouté, false sinon.
	 */
	boolean ajouterJoueur(Joueur joueur);
	
	/**
	 * Lancer la partie. Le joueur qui commence est tiré au hasard.
	 * @param nom
	 * @return Partie
	 */
	boolean commencerPartie(Partie partie);
	
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
	
	/**
	 * Ajout d'un joueur dans la partie
	 * @param partie
	 * @param joueur
	 */
	boolean ajouterJoueur(Partie partie, Joueur joueur);
	
	/**
	 * Affiche le vainqueur de la partie
	 * @param partie
	 * @return vainqueur
	 */ 
	Joueur afficherVainqueur(Partie partie);
	
	/**
	 * 
	 * @param partie
	 * @return liste joueurs
	 */
	List<Joueur> listeJoueursPartie(Partie partie);
}
