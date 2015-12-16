package be.ipl.projet_ejb.usecases;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Joueur;
@Remote
public interface GestionJoueurs {

	/**
	 * Cr�er un nouveau joueur dans la base de donn�es. Le mot de passe
	 * est crypt� dans la DB.
	 * @param prenom
	 * @param pseudo
	 * @param mdp
	 * @return joueur cr�� dans la DB
	 */
	Joueur creerJoueur(String prenom,String pseudo, String mdp);
	
	/**
	 * @param pseudo du joueur � trouver
	 * @return joueur trouv� dans la DB
	 */
	Joueur rechercher(String pseudo);
	
	/**
	 * @param pseudo
	 * @param mdp
	 * @return joueur connect�, null si probl�me dans login
	 */
	Joueur login(String pseudo, String mdp);
}
