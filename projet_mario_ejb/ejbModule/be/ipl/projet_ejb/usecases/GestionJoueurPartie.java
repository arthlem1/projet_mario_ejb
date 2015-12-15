package be.ipl.projet_ejb.usecases;

import java.util.List;

import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.Partie;

public interface GestionJoueurPartie {
	/**
	 * Le joueur tire une carte qui sera ajoutée dans sa main.
	 * @param joueur.
	 */
	void tirerUneCarte(Joueur joueur);
	
	/**
	 * Méthode qui va activer l'effet de la carte.
	 * @param carte.
	 */
	void utiliserCarte(Carte carte);
	
	/**
	 * lister les cartes qui peuvent être utilisées avec le nombre de 
	 * Wasabis passés en paramètre.
	 * @param nbWasabi
	 * @return liste de toutes les cartes
	 */
	List<Carte> listerCartesUtilisables(int nbWasabi);
	
	/**
	 * lister toutes les cartes de ce joueur
	 * @param joueur qui possède les cartes
	 * @return
	 */
	List<Carte> listerCartes(Joueur joueur);
	
	/**
	 * Lancer les dés du joueur et lister le résultat
	 * @return liste des dés lancés.
	 */
	List<De> lancerDes();
	
	/**
	 * Méthode qui permet à un joueur (donneur) de donner ses dés à un
	 * autre joueur (receveur). Il peut en envoyer un ou plusieurs selon son lancé.
	 * @param donneur
	 * @param receveur
	 */
	void donnerDe(Joueur donneur, Joueur receveur, int nbDes);
	
	/**
	 * @param liste des dés lancés par le joueur.
	 * @return nombre de dés pouvant être donnés.
	 */
	int nbFaceDons(List<De> liste);
	
	/**
	 * @param liste des dés lancés par le joueur.
	 * @return nombre de dés pouvant permettre le tirage d'une carte.
	 */
	int nbFaceCartes(List<De> liste);
	
	/**
	 * @param liste des dés lancés par le joueur
	 * @return nombre de dés permettant l'utilisation d'une carte
	 */
	int nbFaceWasabi(List<De> liste);
	
	/**
	 * Ajout d'un joueur dans la partie
	 * @param partie
	 * @param joueur
	 */
	void ajouterJoueur(Partie partie, Joueur joueur);
	
	/**
	 * Suppresion de dés pour le joueur
	 * @param nbDes nombre de dés à supprimer
	 * @param joueur joueur dont les dés sont supprimés.
	 */
	void supprimerDe(int nbDes, Joueur joueur);
}
