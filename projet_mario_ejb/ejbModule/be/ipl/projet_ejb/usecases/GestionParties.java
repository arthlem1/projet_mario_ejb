package be.ipl.projet_ejb.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.MaxJoueursException;
import be.ipl.projet_ejb.exceptions.PartieDejaEnCoursException;
import be.ipl.projet_ejb.exceptions.PasAssezDeJoueursException;

@Remote
public interface GestionParties {

	/**
	 * Création de la partie. La date et l'heure de création sont enregistrés.
	 * @param nom
	 * @param joueur
	 * @return 
	 * @throws PartieDejaEnCoursException 
	 * @throws MaxJoueursException 
	 */
	Partie creerPartie(String nom, Joueur joueur) throws PartieDejaEnCoursException, MaxJoueursException;
	/**
	 * recherche de la partie avec son nom. Si elle n'existe pas,
	 * la méthode renvoie null.
	 * @param nom
	 * @return Partie
	 */
	Partie rechercherPartie(String nom);
	
	
	/**
	 * Lancer la partie. Le joueur qui commence est tiré au hasard.
	 * @param nom
	 * @return Partie
	 * @throws PasAssezDeJoueursException 
	 */
	boolean commencerPartie(Partie partie) throws PasAssezDeJoueursException;
	
	/**
	 * Passer au joueur suivant.
	 * @param partie
	 * @param suivant
	 * @return Partie MAJ
	 */
	Partie joueurSuivant(Partie partie);
	
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
	 * @throws MaxJoueursException 
	 */
	boolean ajouterJoueur(Partie partie, Joueur joueur) throws MaxJoueursException;
	
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
	
	/**
	 * GetPartieCourante
	 * @return
	 */
	Partie getPartieInitiale();
	
	Partie getPartieEnCours();
	
	Partie initialiserPioche(Partie partie);
	
	Partie initialiserMainsCartes(Partie partie);
	
}
