package be.ipl.projet_ejb.daoimpl;

import be.ipl.projet_ejb.domaine.De;

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
	
	
	
}
