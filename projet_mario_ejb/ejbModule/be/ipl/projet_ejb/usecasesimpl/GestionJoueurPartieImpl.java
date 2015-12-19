package be.ipl.projet_ejb.usecasesimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.InitDB;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;
import be.ipl.projet_ejb.exceptions.PiocheVideException;
import be.ipl.projet_ejb.strategy.Strategy;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.util.Util;

@Stateless
@Startup
public class GestionJoueurPartieImpl implements GestionJoueurPartie {

	@EJB
	private DeDaoImpl deDao;
	@EJB
	private PartieDaoImpl partieDao;
	@EJB
	private JoueurPartieDaoImpl joueurPartieDao;
	@EJB
	private JoueurDaoImpl joueurDao;
	@EJB
	private InitDB initDB;

	private Map<Integer, Strategy> effetCarte = Strategy.initialiser();

	int faceW = 0, faceD = 0, faceC = 0;
	Map<String, Integer> faces;

	public void jouerTourCarte(Joueur j, Partie p, boolean play) {
		p = partieDao.recharger(p.getId());
		j = joueurDao.recharger(j.getId());
		if (!play) {
			partieDao.passerAuJoueurSuivant(p);
		}
	}

	@Override
	public Partie tirerUneCarte(Partie partie, Joueur joueur) throws PiocheVideException {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		joueur = joueurDao.recharger(joueur.getId());
		Carte carte = partieDao.piocher(partie);// premiere carte
		JoueurPartie joueurPartie = joueurPartieDao.getPlayer(joueur.getId(), partie.getId());
		joueurPartieDao.rajouterCarte(joueurPartie, carte);
		return partie;
	}

	@Override
	public Partie utiliserCarte(Carte carte, Partie partie, Joueur joueur, Joueur cible, boolean clockwize)
			throws JoueurNonTrouveException {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		Util.checkObject(carte);
		partie = partieDao.recharger(partie.getId());
		joueur = joueurDao.recharger(joueur.getId());
		if (cible != null)
			cible = joueurDao.recharger(cible.getId());
		partieDao.passerAuJoueurSuivant(partie);
		effetCarte.get(carte.getCodeEffet()).effectuer(deDao, partieDao, joueurPartieDao, partie, joueur, cible,
				clockwize);
		joueurPartieDao.retirerCarte(joueur, partie, carte);
		if (partie.getJoueur_courant().getMainsDe().isEmpty()) {
			partie.setVainqueur(joueurDao.rechercher(partie.getJoueur_courant().getJoueur().getId()));
		}
		return partie;
	}

	@Override
	public List<Carte> listerCartesUtilisables(int nbWasabi, Partie partie) {
		Util.checkPositiveOrZero(nbWasabi);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		List<Carte> utilisable = new ArrayList<Carte>();
		List<Carte> main = partie.getJoueur_courant().getMainsCarte();
		for (Carte carte : main) {
			if (carte.getCout() <= nbWasabi)
				utilisable.add(carte);
		}
		return utilisable;
	}

	@Override
	public List<Carte> listerCartes(Joueur joueur, Partie partie) {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		joueur = joueurDao.recharger(joueur.getId());
		return joueurPartieDao.getCartes(joueur, partie);
	}

	@Override
	public Map<String, Integer> lancerDes(Joueur joueur, Partie partie, int nbDes) {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		joueur = joueurDao.recharger(joueur.getId());
		faceW = 0;
		faceD = 0;
		faceC = 0;
		faces = new HashMap<String, Integer>();
		JoueurPartie jp = joueurPartieDao.getPlayer(joueur.getId(), partie.getId());
		System.out.println("Main Dés: " + jp.getMainsDe().size());
		De de = deDao.recharger(jp.getMainsDe().get(0).getId());
		Random random = new Random();
		for (int i = 0; i < nbDes; i++) {
			int val = random.nextInt(6) + 1;
			valeurFace(de, val);
		}
		faces.put("w", faceW);
		faces.put("d", faceD);
		faces.put("c", faceC);
		return Collections.unmodifiableMap(faces);
	}

	private void valeurFace(De de, int val) {
		if (val <= 2) {
			de.setValeur("w");
			faceW++;
		} else if (val == 6) {
			de.setValeur("d");
			faceD++;
		} else {
			de.setValeur("c");
			faceC++;
		}
	}

	@Override
	public void donnerDe(Joueur donneur, Joueur receveur, int nbDes, Partie partie) {
		Util.checkObject(donneur);
		Util.checkObject(receveur);
		Util.checkPositiveOrZero(nbDes);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		donneur = joueurDao.recharger(donneur.getId());
		receveur = joueurDao.recharger(receveur.getId());
		joueurPartieDao.transfererDe(donneur, receveur, nbDes, partie);
		// if (partie.getJoueur_courant().getMainsDe().isEmpty()) {
		// partie.setVainqueur(joueurDaoImpl.rechercher(partie.getJoueur_courant().getJoueur().getId()));
		// }
	}

	@Override
	public void supprimerDe(int nbDes, Partie partie) {
		Util.checkPositiveOrZero(nbDes);
		Util.checkObject(partie);
		partie = partieDao.recharger(partie.getId());
		for (int i = 0; i < nbDes; i++) {
			JoueurPartie joueurCourant = joueurPartieDao.recharger(partie.getJoueur_courant().getId());
			if (joueurPartieDao.retirerDe(joueurCourant.getJoueur().getId(), partie) == 0) {
				partie.setVainqueur(joueurDao.rechercher(partie.getJoueur_courant().getJoueur().getId()));
			}
		}
	}

	@Override
	public boolean lancerTour(Joueur j, Partie p) {
		p = partieDao.recharger(p.getId());
		j = joueurDao.recharger(j.getId());
		System.out.println("id j " + j.getId());
		System.out.println("id p " + p.getId());
		return !joueurPartieDao.isBlocked(j.getId(), p.getId());
	}

	@Override
	public int ordreJoueur(int idJoueur, int idPartie) {
		return joueurPartieDao.getPlayer(idJoueur, idPartie).getOrdreJoueurs();
	}

	@Override
	public int nbDe(Joueur j, Partie p) {
		p = partieDao.recharger(p.getId());
		j = joueurDao.recharger(j.getId());
		return joueurPartieDao.getNbDe(j, p);
	}

	@Override
	public boolean besoinCible(Carte carte) {
		int besoinCible = carte.getCodeEffet();
		return (besoinCible >= 4 && besoinCible <= 6) || besoinCible == 9;
	}

	@Override
	public int supprimerJoueurPartie(Joueur joueur, Partie partie) {

		partie = partieDao.recharger(partie.getId());
		joueur = joueurDao.recharger(joueur.getId());
		JoueurPartie joueurPartie = joueurPartieDao.getPlayer(joueur.getId(), partie.getId());

		partie.supprimer(joueurPartie);
		joueur.supprimer(joueurPartie);

		partie = partieDao.mettreAJour(partie);

		joueurPartieDao.supprimer(joueurPartie.getId());
		joueur = joueurDao.mettreAJour(joueur);

		return partie.getListeJoueurs().size();
	}
}
