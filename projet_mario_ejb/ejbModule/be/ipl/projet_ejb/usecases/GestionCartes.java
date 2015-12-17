package be.ipl.projet_ejb.usecases;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Carte;

@Remote
public interface GestionCartes {

	/**
	 * Va rechercher la carte dans la base de données.
	 * Renvoie null si introuvable.
	 * @param id.
	 * @return Carte.
	 */
	Carte rechercherCarte(int id);
	
	/**
	 * Renvoie la description d'une carte
	 * @param codeEffet
	 * @return
	 */
	String descriptionCarte(int codeEffet);
	
}
