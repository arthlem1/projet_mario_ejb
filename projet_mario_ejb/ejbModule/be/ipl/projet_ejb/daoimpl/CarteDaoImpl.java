package be.ipl.projet_ejb.daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Carte;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class CarteDaoImpl extends DaoImpl<Integer, Carte> {

	public CarteDaoImpl() {
		super(Carte.class);
	}
	
	public Carte rechercher(int id) {
		String queryString = "select c from Carte c where c.id = ?1";
		return recherche(queryString, id);
	}

}
