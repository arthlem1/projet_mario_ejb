package be.ipl.projet_ejb.usecases;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Joueur;
@Remote
public interface GestionJoueurs {

	/**
	 * Créer un nouveau joueur dans la base de données. Le mot de passe
	 * est crypté dans la DB.
	 * @param prenom
	 * @param pseudo
	 * @param mdp
	 * @return joueur créé dans la DB
	 */
	Joueur creerJoueur(String prenom,String pseudo, String mdp);
	
	/**
	 * @param pseudo du joueur à trouver
	 * @return joueur trouvé dans la DB
	 */
	Joueur rechercher(String pseudo);
	
	/**
	 * @param pseudo
	 * @param mdp
	 * @return joueur connecté, null si problème dans login
	 */
	Joueur login(String pseudo, String mdp);
}
