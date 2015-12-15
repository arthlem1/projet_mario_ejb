package be.ipl.projet_ejb.usecases;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;

public interface GestionParties {

	/**
	 * Cr�ation de la partie. La date et l'heure de cr�ation sont enregistr�s.
	 * @param nom
	 */
	void creerPartie(String nom);
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
}
