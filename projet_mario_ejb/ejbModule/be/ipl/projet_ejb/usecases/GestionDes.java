package be.ipl.projet_ejb.usecases;

import java.util.List;

import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;

public interface GestionDes {
	
	/**
	 * Recherche dans la DB
	 * @param joueur poss�dant les d�s
	 * @return liste des d�s
	 */
	List<De> listerDes(Joueur joueur);
	
	/**
	 * Recherche dans la DB.
	 * @param de � trouver
	 * @return de
	 */
	De recherche(De de);
}
