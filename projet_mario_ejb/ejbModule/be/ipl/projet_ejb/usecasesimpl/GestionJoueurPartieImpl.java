package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionJoueurPartie;
@Singleton
@Startup
public class GestionJoueurPartieImpl implements GestionJoueurPartie {

	
	
	@Override
	public void tirerUneCarte(Joueur joueur) {
		//TODO tirer une carte
	}

	@Override
	public void utiliserCarte(Carte carte) {
		//TODO utiliser une carte
	}

	@Override
	public List<Carte> listerCartesUtilisables(int nbWasabi) {
		// TODO lister cartes utilisables
		return null;
	}

	@Override
	public List<Carte> listerCartes(Joueur joueur) {
		// TODO lister les cartes
		return null;
	}

	@Override
	public List<De> lancerDes() {
		// TODO lancer les dés
		return null;
	}

	@Override
	public void donnerDe(Joueur donneur, Joueur receveur, int nbDes) {
		// TODO donner des dés
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
