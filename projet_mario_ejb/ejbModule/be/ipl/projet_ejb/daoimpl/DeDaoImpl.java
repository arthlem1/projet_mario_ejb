package be.ipl.projet_ejb.daoimpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.util.Util;

@Stateless
@LocalBean
public class DeDaoImpl extends DaoImpl<Integer, De> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeDaoImpl(){
		super(De.class);
	}
	
	public DeDaoImpl(Class<De> entityClass) {
		super(entityClass);
	}
	
	public List<De> lister(Joueur joueur){
		String query = "select d.valeur from des d, joueurs j WHERE j.id=?1";
		return liste(query, joueur.getId());
	}
	
	public void supprimer(De entite) {
		super.supprimer(entite.getId());
	}
	
	public De rechercher(De entite) {
		return super.rechercher(entite.getId());
	}
	
	
	
}
