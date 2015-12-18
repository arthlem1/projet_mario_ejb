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

	public int getNbDe(Joueur j, Partie p) {
		int i = getPlayer(j.getId(), p.getId()).getMainsDe().size();
		return i;

	}

	public void setBlocked(Joueur j, Partie p) {
		getPlayer(j.getId(), p.getId()).setBlocked();
	}

	public List<Carte> getCartes(Joueur j, Partie p) {
		JoueurPartie jp = getPlayer(j.getId(), p.getId());
		List<Carte> retour = jp.getMainsCarte();
		return retour;
	}

	public int retirerDe(int joueurId, Partie p) {
		JoueurPartie jp = getPlayer(joueurId, p.getId());
		jp.getMainsDe().remove(jp.getMainsDe().size()-1);
		//mettreAJour(jp);
		return jp.getMainsDe().size();
	}

	public int addDe(Joueur j, Partie p, De d) {
		JoueurPartie jp = getPlayer(j.getId(), p.getId());
		jp.getMainsDe().add(d);
		//mettreAJour(jp);
		return jp.getMainsDe().size();
	}

	public int transfererDe(Joueur donnant, Joueur recevant, int nb, Partie p) {
		JoueurPartie recever = getPlayer(recevant.getId(), p.getId());
		JoueurPartie giver = getPlayer(donnant.getId(), p.getId());
		recever.getMainsDe().add(giver.getMainsDe().remove(giver.getMainsDe().size()-1));
		//mettreAJour(giver);
		//mettreAJour(recever);
		return giver.getMainsDe().size();
	}

	public boolean retirerCarte(Joueur j, Partie p, Carte c) {
		JoueurPartie jp = getPlayer(j.getId(), p.getId());
		if (!jp.getMainsCarte().contains(c))
			return false;
		jp.getMainsCarte().remove(c);
		// mettreAJour(jp);
		return true;
	}

	public boolean transfererCarte(Joueur giver, Joueur receiver, Partie p, Carte c) {
		JoueurPartie jg = getPlayer(giver.getId(), p.getId());
		JoueurPartie jr = getPlayer(receiver.getId(), p.getId());
		if (!jg.getMainsCarte().contains(c) || jr.getMainsCarte().contains(c)) {
			return false;
		}
		jg.getMainsCarte().remove(c);
		jr.getMainsCarte().add(c);
		//mettreAJour(jr);
		//mettreAJour(jg);
		return true;
	}

	public boolean rajouterCarte(JoueurPartie joueurPartie, Carte c) {
		joueurPartie = rechercher(joueurPartie.getId());
		if (joueurPartie.getMainsCarte().contains(c))
			return false;
		joueurPartie.getMainsCarte().add(c);
		//joueurPartie = mettreAJour(joueurPartie);
		return true;
	}

	public JoueurPartie getPlayer(int jid, int pid) {
		String query = "Select jp FROM JoueurPartie jp " + "WHERE jp.joueur.id=?1 " + "AND jp.partie.id=?2 ";
		return recherche(query, jid, pid);
	}

	public boolean isBlocked(int id, int id2) {
		return getPlayer(id, id2).isBlocked();
	}
	
	public void supprimerJoueurPartie(int joueur, int partie){
		super.supprimer(getPlayer(joueur, partie).getId());
	}
	
}
