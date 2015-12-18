package be.ipl.projet_ejb.usecases;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.De;

@Remote
public interface GestionDes {

	/**
	 * Recherche dans la DB.
	 * 
	 * @param de
	 *            à trouver
	 * @return de
	 */
	De recherche(De de);
}
