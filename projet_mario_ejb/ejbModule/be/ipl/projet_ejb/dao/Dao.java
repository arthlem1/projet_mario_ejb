package be.ipl.projet_ejb.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K, E> extends Serializable {
	E rechercher(K id);

	E enregistrer(E entite);

	E mettreAJour(E entite);

	E recharger(K id);

	void supprimer(K id);

	List<E> lister();
}
