package be.ipl.projet_ejb.usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.util.Util;
 
@Singleton
@Startup
public class GestionPartiesImpl implements GestionParties {

	@EJB
	PartieDaoImpl partieDao;

	public enum Etat {
		INITIAL {
			boolean ajouterJoueur(Joueur joueur, Partie partie, GestionPartiesImpl gpi) {
				Util.checkObject(joueur);
				Util.checkObject(partie);
				return gpi.partieDao.ajouterJoueur(partie, joueur);
			}
			public boolean commencerPartie(Partie partie,GestionPartiesImpl gpi) {
				partie.setEtat(EN_COURS);
				return true;
			}
		},
		EN_COURS{
			
		}, FINI{
			public Joueur afficherVainqueur(Partie partie , GestionPartiesImpl gpi) {
				return gpi.afficherVainqueur(partie);
			}
		};

		boolean ajouterJoueur(Joueur joueur, Partie partie, GestionPartiesImpl gpi) {
			return false;
		}
		
		public boolean commencerPartie(Partie partie,GestionPartiesImpl gpi) {
			return false;
		}
		
		public Joueur afficherVainqueur(Partie partie , GestionPartiesImpl gpi) {
			return null;
		}
	}

	@Override
	public void creerPartie(String nom, Joueur createur) {
		Util.checkString(nom);
		Util.checkObject(createur);
		partieDao.creerPartie(nom, createur);
	}

	@Override
	public Partie rechercherPartie(String nom) {
		Util.checkString(nom);
		return partieDao.rechercher(nom);
	}

	@Override
	public boolean ajouterJoueur(Joueur joueur) {
		Util.checkObject(joueur);
		return false;
	}

	@Override
	public Partie joueurSuivant(Partie partie, JoueurPartie suivant) {
		Util.checkObject(suivant);
		Util.checkObject(partie);
		return partieDao.passerAuJoueurSuivant(partie, suivant);
	}

	@Override
	public void changerSens(Partie partie) {
		Util.checkObject(partie);
		partieDao.changerSens(partie);
	}

	@Override
	public List<Partie> listerPartiesJouees(Joueur joueur) {
		Util.checkObject(joueur);
		return partieDao.listerPartiesJouees(joueur);
	}

	@Override
	public boolean ajouterJoueur(Partie partie, Joueur joueur) {
		return partie.getEtat().ajouterJoueur(joueur, partie, this);
	}

	@Override
	public boolean commencerPartie(Partie partie) {
		return partie.getEtat().commencerPartie(partie, this);
	}

	@Override
	public Joueur afficherVainqueur(Partie partie) {
		return partie.getEtat().afficherVainqueur(partie, this);
	}

	
}
