package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.CarteDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.InitDB;
import be.ipl.projet_ejb.usecases.GestionCartes;
import be.ipl.projet_ejb.util.Util;
@Singleton
@Startup
public class GestionCartesImpl implements GestionCartes {

	@EJB private CarteDaoImpl	carteDao;
	@EJB private InitDB initDB;
	
	@Override
	public Carte rechercherCarte(int id) {
		Util.checkPositive(id);
		return carteDao.rechercher(id);
	}

	@Override
	public String descriptionCarte(int codeEffet) {
		List<Carte> cartes = initDB.getWazabi().getCarte();
		for (Carte carte : cartes) {
			if(carte.getCodeEffet()==codeEffet) return carte.getEffet();
		}
		return "Description non trouvée";
	}

}
