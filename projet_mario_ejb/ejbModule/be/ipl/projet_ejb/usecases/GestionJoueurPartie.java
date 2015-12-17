package be.ipl.projet_ejb.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.Face;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.PiocheVideException;

@Remote 
public interface GestionJoueurPartie {
	/**
	 * Le joueur tire une carte qui sera ajoutée dans sa main.
	 * @param joueurPartie.// adapter DAo car doublon dans les params
	 * @throws PiocheVideException si plus de carte dans la pioche
	 */
	void tirerUneCarte(Partie partie, Joueur joueur) throws PiocheVideException;
	
	/**
	 * Méthode qui va activer l'effet de la carte.
	 * @param carte.//idem
	 */
	void utiliserCarte(Carte carte, Partie partie, Joueur joueur, Joueur cible);
	
	/**
	 * lister les cartes qui peuvent être utilisées avec le nombre de 
	 * Wasabis passés en paramètre.
	 * @param nbWasabi
	 * @return liste de toutes les cartes
	 */
	List<Carte> listerCartesUtilisables(int nbWasabi, Partie partie);
	
	/**
	 * lister toutes les cartes de ce joueur
	 * @param joueur qui possède les cartes
	 * @return
	 */
	List<Carte> listerCartes(Joueur joueur, Partie partie);
	
	/**
	 * Lancer les dés du joueur et lister le résultat
	 * @return liste des dés lancés.
	 */
	List<Face> lancerDes(Joueur joueur, Partie partie);
	
	/**
	 * Méthode qui permet à un joueur (donneur) de donner ses dés à un
	 * autre joueur (receveur). Il peut en envoyer un ou plusieurs selon son lancé.
	 * @param donneur
	 * @param receveur
	 */
	void donnerDe(Joueur donneur, Joueur receveur, int nbDes, Partie partie);
	
	/**
	 * @param liste des dés lancés par le joueur.
	 * @return nombre de dés pouvant être donnés.
	 */
	int nbFaceDons(List<Face> liste);
	
	/**
	 * @param liste des dés lancés par le joueur.
	 * @return nombre de dés pouvant permettre le tirage d'une carte.
	 */
	int nbFaceCartes(List<Face> liste);
	
	/**
	 * @param liste des dés lancés par le joueur
	 * @return nombre de dés permettant l'utilisation d'une carte
	 */
	int nbFaceWasabi(List<Face> liste);
	
	/**
	 * Suppresion de dés pour le joueur
	 * @param nbDes nombre de dés à supprimer
	 * @param joueur joueur dont les dés sont supprimés.
	 */
	void supprimerDe(int nbDes, Partie partie);
}
