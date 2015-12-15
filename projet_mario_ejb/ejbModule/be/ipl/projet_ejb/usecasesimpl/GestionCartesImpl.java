package be.ipl.projet_ejb.usecasesimpl;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.CarteDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.usecases.GestionCartes;
import be.ipl.projet_ejb.util.Util;
@Singleton
@Startup
public class GestionCartesImpl implements GestionCartes {

	@EJB CarteDaoImpl	carteDao;
	
	@Override
	public Carte rechercherCarte(int id) {
		Util.checkPositive(id);
		return carteDao.rechercher(id);
	}

}
