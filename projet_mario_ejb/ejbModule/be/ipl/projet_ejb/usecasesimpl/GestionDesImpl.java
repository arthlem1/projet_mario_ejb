package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.usecases.GestionDes;
import be.ipl.projet_ejb.util.Util;

@Stateless
@Startup
public class GestionDesImpl implements GestionDes {

	@EJB
	DeDaoImpl deDao;

	@Override
	public De recherche(De de) {
		Util.checkObject(de);
		return deDao.rechercher(de.getId());
	}
}
