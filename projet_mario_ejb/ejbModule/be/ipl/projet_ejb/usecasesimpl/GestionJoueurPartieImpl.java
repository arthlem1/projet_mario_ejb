package be.ipl.projet_ejb.usecasesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Face;
import be.ipl.projet_ejb.domaine.InitDB;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.JoueurNonTrouveException;
import be.ipl.projet_ejb.exceptions.PiocheVideException;
import be.ipl.projet_ejb.strategy.Strategy;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
import be.ipl.projet_ejb.util.Util;

@Stateless
public class GestionJoueurPartieImpl implements GestionJoueurPartie {

	@EJB
	private DeDaoImpl deDao;
	@EJB
	private PartieDaoImpl partieDao;
	@EJB
	private JoueurPartieDaoImpl joueurPartieDao;
	@EJB
	private JoueurDaoImpl joueurDaoImpl;
	@EJB
	private InitDB initDB;

	private Map<Integer, Strategy> effetCarte = Strategy.initialiser();

	public void jouerTourCarte(Joueur j, Partie p, boolean play) {
		if (!play) {
			partieDao.passerAuJoueurSuivant(p);
		}
	}

	@Override
	public void tirerUneCarte(Partie partie, Joueur joueur) throws PiocheVideException {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		List<Carte> pioche = partie.getPioche();
		if (pioche.size() == 0) {
			throw new PiocheVideException("Il n'y a plus de cartes dans la pioche");
			/*
			 * TODO à discuter. Selon les consignes, si plus de carte dans la
			 * pioche, prendre une carte dans la main d'un autre
			 */
		}
		Carte carte = pioche.remove(0);// premiere carte
		joueurPartieDao.rajouterCarte(joueur, partie, carte);
	}

	@Override
	public void utiliserCarte(Carte carte, Partie partie, Joueur joueur, Joueur cible) throws JoueurNonTrouveException {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		Util.checkObject(carte);
		partieDao.passerAuJoueurSuivant(partie);
		effetCarte.get(carte.getCodeEffet()).effectuer(deDao, partieDao, joueurPartieDao, partie, joueur, cible);
		joueurPartieDao.retirerCarte(joueur, partie, carte);
		if (partie.getJoueur_courant().getMainsDe().isEmpty()) {
			partie.setVainqueur(joueurDaoImpl.rechercher(partie.getJoueur_courant().getJoueur().getId()));
		}
	}

	@Override
	public List<Carte> listerCartesUtilisables(int nbWasabi, Partie partie) {
		Util.checkPositiveOrZero(nbWasabi);
		Util.checkObject(partie);
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
		return joueurPartieDao.getCartes(joueur, partie);
	}

	@Override
	public List<Face> lancerDes(Joueur joueur, Partie partie) {
		Util.checkObject(joueur);
		Util.checkObject(partie);
		List<Face> faces = new ArrayList<>();
		De de = initDB.getWazabi().getDe();
		int nbDesJoueur = joueurPartieDao.getNbDe(joueur, partie);
		for (int i = 0; i < nbDesJoueur; i++) {
			Random random = new Random();
			Face face = de.getFace().get(random.nextInt(6) + 1);
			faces.add(face);
		}
		return faces;
	}

	@Override
	public void donnerDe(Joueur donneur, Joueur receveur, int nbDes, Partie partie) {
		Util.checkObject(donneur);
		Util.checkObject(receveur);
		Util.checkPositiveOrZero(nbDes);
		Util.checkObject(partie);
		joueurPartieDao.transfererDe(donneur, receveur, nbDes, partie);
		if (partie.getJoueur_courant().getMainsDe().isEmpty()) {
			partie.setVainqueur(joueurDaoImpl.rechercher(partie.getJoueur_courant().getJoueur().getId()));
		}
	}

	@Override
	public void supprimerDe(int nbDes, Partie partie) {
		Util.checkPositiveOrZero(nbDes);
		Util.checkObject(partie);
		for (int i = 0; i < nbDes; i++) {
			if (joueurPartieDao.retirerDe(partie.getJoueur_courant().getJoueur().getId(), partie) == 0) {
				partie.setVainqueur(joueurDaoImpl.rechercher(partie.getJoueur_courant().getJoueur().getId()));
			}
		}
	}

	@Override
	public int nbFaceDons(List<Face> liste) {
		Util.checkObject(liste);
		int compteur = 0;
		for (Face face : liste) {
			if (face.getIdentif() == "d")
				compteur++;
		}
		return compteur;
	}

	@Override
	public int nbFaceCartes(List<Face> liste) {
		Util.checkObject(liste);
		int compteur = 0;
		for (Face face : liste) {
			if (face.getIdentif() == "c")
				compteur++;
		}
		return compteur;
	}

	@Override
	public int nbFaceWasabi(List<Face> liste) {
		Util.checkObject(liste);
		int compteur = 0;
		for (Face face : liste) {
			if (face.getIdentif() == "w")
				compteur++;
		}
		return compteur;
	}

	@Override
	public boolean lancerTour(Joueur j, Partie p) {
		j = joueurDaoImpl.rechercher(j.getId());
		p = partieDao.rechercher(p.getNom());
		return !joueurPartieDao.isBlocked(j.getId(), p.getId());
	}

	@Override
	public int ordreJoueur(int idJoueur, int idPartie) {
		return joueurPartieDao.getPlayer(idJoueur, idPartie).getOrdreJoueurs();
	}
}
