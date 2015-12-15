package be.ipl.projet_ejb.usecases;

import javax.ejb.Remote;

import be.ipl.projet_ejb.domaine.Joueur;
@Remote
public interface GestionJoueurs {

	Joueur creerJoueur(String prenom,String pseudo, String mdp);
	Joueur rechercher(String pseudo);
	boolean login(String pseudo, String mdp);
}
