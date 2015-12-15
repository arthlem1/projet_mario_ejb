package be.ipl.projet_ejb.daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Face;

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
	
	public Face getFace (De d,int num){
		return d.getFace().get(num);
	}
	
	public void supprimer(De entite) {
		super.supprimer(entite.getId());
	}
	
	public De rechercher(De entite) {
		return super.rechercher(entite.getId());
	}
	
	
	
}
