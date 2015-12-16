package be.ipl.projet_ejb.daoimpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;


@Stateless
@LocalBean
public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl() {
		super(JoueurPartie.class);
	}
	
	public int getNbDe(Joueur j,Partie p){
		int i = setQuery(j.getId(),p.getId()).getMainsCarte().size();
		return i;
		
	}
	
	public List<Carte> getCartes(Joueur j,Partie p){
		JoueurPartie jp= setQuery(j.getId(),p.getId());
		List<Carte> retour = jp.getMainsCarte();
		return retour;
	}
	
	public int retirerDe(Joueur j, Partie p){
		JoueurPartie jp= setQuery(j.getId(),p.getId());
		jp.getMainsDe().remove(jp.getMainsDe().size());
		mettreAJour(jp);
		return jp.getMainsDe().size();
	}
	
	public int addDe(Joueur j, Partie p,De d){
		JoueurPartie jp= setQuery(j.getId(),p.getId());
		jp.getMainsDe().add(d);
		mettreAJour(jp);
		return jp.getMainsDe().size();
	}

	public int transfererDe(Joueur donnant, Joueur recevant, int nb,Partie p){
		JoueurPartie recever= setQuery(recevant.getId(),p.getId());
		JoueurPartie giver= setQuery(donnant.getId(),p.getId());
		recever.getMainsDe().add(giver.getMainsDe().remove(giver.getMainsDe().size()));
		mettreAJour(giver);
		mettreAJour(recever);
		return giver.getMainsDe().size();
	}
	
	public boolean retirerCarte(Joueur j,Partie p, Carte c){
		JoueurPartie jp= setQuery(j.getId(),p.getId());
		if(!jp.getMainsCarte().contains(c))
			return false;
		jp.getMainsCarte().remove(c);
		mettreAJour(jp);
		return true;
	}
	
	public boolean transfererCarte(Joueur giver, Joueur receiver, Partie p, Carte c){
		JoueurPartie jg= setQuery(giver.getId(),p.getId());
		JoueurPartie jr= setQuery(receiver.getId(),p.getId());
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
		JoueurPartie jp= setQuery(j.getId(),p.getId());
		if(jp.getMainsCarte().contains(c))
			return false;
		jp.getMainsCarte().add(c);
		mettreAJour(jp);
		return true;
	}
	
	private JoueurPartie setQuery(int jid, int pid){
		String query="Select jp FROM JoueurPartie jp "
				+ "WHERE jp.joueurPartiePK.joueur_id=?1 "
				+ "AND jp.joueurPartiePK.partie_id=?2 ";
		return recherche(query,jid,pid);
	}
}
