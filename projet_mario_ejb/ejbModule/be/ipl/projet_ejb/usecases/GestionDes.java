package be.ipl.projet_ejb.usecases;

import java.util.List;

import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;

public interface GestionDes {
	
	/**
	 * Recherche dans la DB
	 * @param joueur possédant les dés
	 * @return liste des dés
	 */
	List<De> listerDes(Joueur joueur);
	
	/**
	 * Recherche dans la DB.
	 * @param de à trouver
	 * @return de
	 */
	De recherche(De de);
}
