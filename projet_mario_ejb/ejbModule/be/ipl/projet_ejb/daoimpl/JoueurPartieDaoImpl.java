package be.ipl.projet_ejb.daoimpl;

import java.util.List;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;

@SuppressWarnings("serial")
public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl(Class<JoueurPartie> entityClass) {
		super(entityClass);
	}
	
	public int getNbDe(Joueur j,Partie p){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		int i=recherche(query,j.getId(),p.getId()).getMainsCarte().size();
		return i;
		
	}
	
	public List<Carte> getCartes(Joueur j,Partie p){
		String query="Select jp FROM  JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jp= recherche(query,j.getId(),p.getId());
		List<Carte> retour = jp.getMainsCarte();
		return retour;
	}
	
	public int retirerDe(Joueur j, Partie p){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jp= recherche(query,j.getId(),p.getId());
		jp.getMainsDe().remove(jp.getMainsDe().size());
		mettreAJour(jp);
		return jp.getMainsDe().size();
	}
	
	public int addDe(Joueur j, Partie p,De d){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jp= recherche(query,j.getId(),p.getId());
		jp.getMainsDe().add(d);
		mettreAJour(jp);
		return jp.getMainsDe().size();
	}

	public int transfererDe(Joueur donnant, Joueur recevant, int nb,Partie p){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie recever= recherche(query,recevant.getId(),p.getId());
		query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie giver= recherche(query,donnant.getId(),p.getId());
		recever.getMainsDe().add(giver.getMainsDe().remove(giver.getMainsDe().size()));
		mettreAJour(giver);
		mettreAJour(recever);
		return giver.getMainsDe().size();
	}
	
	public boolean retirerCarte(Joueur j,Partie p, Carte c){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jp= recherche(query,j.getId(),p.getId());
		if(!jp.getMainsCarte().contains(c))
			return false;
		jp.getMainsCarte().remove(c);
		mettreAJour(jp);
		return true;
	}
	
	public boolean transfererCarte(Joueur giver, Joueur receiver, Partie p, Carte c){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jg= recherche(query,giver.getId(),p.getId());
		 query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jr= recherche(query,receiver.getId(),p.getId());
		if(!jg.getMainsCarte().contains(c)||jr.getMainsCarte().contains(c)){
			return false;
		}
		jg.getMainsCarte().remove(c);
		jr.getMainsCarte().add(c);
		mettreAJour(jr);
		mettreAJour(jg);
		return true;
	}
	
	public boolean rajouterCarte(Joueur j,Partie p, Carte c){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK=?1 "
				+ "AND jp.joueurPartiePK=?2 ";
		JoueurPartie jp= recherche(query,j.getId(),p.getId());
		if(jp.getMainsCarte().contains(c))
			return false;
		jp.getMainsCarte().add(c);
		mettreAJour(jp);
		return true;
	}
	
	
}
