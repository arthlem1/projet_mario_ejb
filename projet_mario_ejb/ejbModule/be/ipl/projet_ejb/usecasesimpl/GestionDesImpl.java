package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.usecases.GestionDes;
import be.ipl.projet_ejb.util.Util;

@Singleton
@Startup
public class GestionDesImpl implements GestionDes {

	@EJB DeDaoImpl	deDao;
	
	@Override
	public List<De> listerDes(Joueur joueur) {
		Util.checkObject(joueur);
		return deDao.lister(joueur);
	}

	@Override
	public De recherche(De de) {
		Util.checkObject(de);
		return deDao.rechercher(de);
	}

}
