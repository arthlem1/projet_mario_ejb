package be.ipl.projet_ejb.usecases;

import be.ipl.projet_ejb.domaine.De;

public interface GestionDes {
	
	/**
	 * Recherche dans la DB.
	 * @param de � trouver
	 * @return de
	 */
	De recherche(De de);
}
