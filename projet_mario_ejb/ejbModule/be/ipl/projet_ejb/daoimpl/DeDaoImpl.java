package be.ipl.projet_ejb.daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.De;

@Stateless
@LocalBean
public class DeDaoImpl extends DaoImpl<Integer, De> {

	public DeDaoImpl(){
		super(De.class);
	}
	
	public DeDaoImpl(Class<De> entityClass) {
		super(entityClass);
	}
	
	public void supprimer(De entite) {
		super.supprimer(entite.getId());
	}
	
	public De rechercher(De entite) {
		return super.rechercher(entite.getId());
	}
	
}
