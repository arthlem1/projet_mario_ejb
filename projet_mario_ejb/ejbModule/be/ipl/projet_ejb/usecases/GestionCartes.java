package be.ipl.projet_ejb.usecases;

import be.ipl.projet_ejb.domaine.Carte;

public interface GestionCartes {

	/**
	 * Va rechercher la carte dans la base de données.
	 * Renvoie null si introuvable.
	 * @param id.
	 * @return Carte.
	 */
	Carte rechercherCarte(int id);
	
	
	
}
