package be.ipl.projet_ejb.daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Joueur;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class JoueurDaoImpl extends DaoImpl<String, Joueur> {
	public JoueurDaoImpl() {
		super(Joueur.class);
	}

	public Joueur rechercher(String pseudo) {
		String queryString = "select j from Joueur j where j.pseudo = ?1";
		return recherche(queryString, pseudo);
	}
	
	//TODO à modifier quand ecryption faite.
	public boolean login(String pseudo, String mdp){
		Joueur joueurDB = rechercher(pseudo);
		if(joueurDB == null)
			return false;
		return joueurDB.getMdp() == mdp;
	}
	
}
