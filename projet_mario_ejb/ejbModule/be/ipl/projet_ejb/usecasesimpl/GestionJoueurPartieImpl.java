package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.daoimpl.DeDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;

@Stateless
public class GestionJoueurPartieImpl implements GestionJoueurPartie {

	@EJB DeDaoImpl deDao;
	@EJB PartieDaoImpl partieDao;
	@EJB JoueurPartieDaoImpl joueurPartieDao;
	
	
	@Override
	public void tirerUneCarte(JoueurPartie joueurPartie, Partie partie, Joueur joueur) {
		List<Carte> pioche = partie.getPioche();
		if(pioche.size()==0){
			//lancer exception PiocheNonExistant
		}
		Carte carte = pioche.get(0);//premiere carte
		pioche.remove(0);
		joueurPartieDao.rajouterCarte(joueur, partie, carte);
	}

	@Override
	public void utiliserCarte(Carte carte, JoueurPartie joueurPartie, Partie partie, Joueur joueur) {
		//voir effet carte 
		joueurPartieDao.retirerCarte(joueur, partie, carte);
	}

	@Override
	public List<Carte> listerCartesUtilisables(int nbWasabi,Partie partie) {
		// pas encore dans Dao
		return null;
	}

	@Override
	public List<Carte> listerCartes(Joueur joueur,Partie partie) {
		return joueurPartieDao.getCartes(joueur, partie);
	}

	@Override
	public List<De> lancerDes() {
		Random random = new Random();
		List<De> listeDes = deDao.lister();
		for (int i = 0; i < listeDes.size(); i++) {
			//getFace
		}
		return null;
	}

	@Override
	public void donnerDe(Joueur donneur, Joueur receveur, int nbDes, Partie partie) {
		joueurPartieDao.transfererDe(donneur, receveur, nbDes, partie);
	}

	@Override
	public int nbFaceDons(List<De> liste) {
		// TODO nombre de face "dés à donner"
		return 0;
	}

	@Override
	public int nbFaceCartes(List<De> liste) {
		// TODO nombre de face "dés à cartes"
		return 0;
	}

	@Override
	public int nbFaceWasabi(List<De> liste) {
		// TODO nombre de face "dés à wasabi"
		return 0;
	}

	@Override
	public void ajouterJoueur(Partie partie, Joueur joueur) {
		// TODO ajouter joueur à la partie
		
	}

	@Override
	public void supprimerDe(int nbDes, Joueur joueur) {
		// TODO suppression d'un dé
		
	}

}
