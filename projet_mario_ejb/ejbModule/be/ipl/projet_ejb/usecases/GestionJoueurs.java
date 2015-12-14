package be.ipl.projet_ejb.usecases;

import be.ipl.projet_ejb.domaine.Joueur;

public interface GestionJoueurs {

	Joueur creerJoueur(String pseudo, String mdp);
}
