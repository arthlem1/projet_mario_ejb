package be.ipl.projet_ejb.usecases;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;

public interface GestionParties {

	/**
	 * Création de la partie. La date et l'heure de création sont enregistrés.
	 * @param nom
	 */
	void creerPartie(String nom);
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
}
