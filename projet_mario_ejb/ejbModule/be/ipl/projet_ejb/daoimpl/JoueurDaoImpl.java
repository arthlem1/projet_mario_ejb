package be.ipl.projet_ejb.daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.util.Util;

@Stateless
@LocalBean
public class JoueurDaoImpl extends DaoImpl<Integer, Joueur> {
	public JoueurDaoImpl() {
		super(Joueur.class);
	}

	public Joueur rechercher(String pseudo) {
		String queryString = "select j from Joueur j where j.pseudo = ?1";
		return recherche(queryString, pseudo);
	}

	public Joueur rechercher(int id) {
		String queryString = "select j from Joueur j where j.id = ?1";
		return recherche(queryString, id);
	}

	public Joueur login(String pseudo, String mdp) {
		Joueur joueurDB = rechercher(pseudo);

		mdp = Util.getMD5(mdp);

		if (joueurDB == null)
			return null;
		else if (joueurDB.getMdp().equals(mdp))
			return joueurDB;

		return null;
	}

}
