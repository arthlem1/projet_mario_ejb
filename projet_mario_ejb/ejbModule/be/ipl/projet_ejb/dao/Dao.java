package be.ipl.projet_ejb.dao;

import java.util.List;

public interface Dao<K, E> {
	E rechercher(K id);

	E enregistrer(E entite);

	E mettreAJour(E entite);

	E recharger(K id);

	void supprimer(K id);

	List<E> lister();
}
