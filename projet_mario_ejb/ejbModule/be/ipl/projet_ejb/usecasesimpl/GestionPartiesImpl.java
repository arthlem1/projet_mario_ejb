package be.ipl.projet_ejb.usecasesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.MaxJoueursException;
import be.ipl.projet_ejb.exceptions.PartieDejaEnCoursException;
import be.ipl.projet_ejb.usecases.GestionParties;
import be.ipl.projet_ejb.util.Util;

@Singleton
@Startup
public class GestionPartiesImpl implements GestionParties {

	@EJB
	private PartieDaoImpl partieDao;
	@EJB
	private JoueurDaoImpl joueurDaoImpl;
	@EJB
	private JoueurPartieDaoImpl joueurPartieDaoImpl;

	public enum Etat {
		INITIAL {
			boolean ajouterJoueur(Joueur joueur, Partie partie, GestionPartiesImpl gpi) throws MaxJoueursException {
				Util.checkObject(joueur);
				Util.checkObject(partie);
				try {
					gpi.joueurPartieDaoImpl.enregistrer(
							new JoueurPartie(joueur, partie, gpi.partieDao.listerJoueursPartie(partie).size()));
				} catch (Exception e) {
					throw new MaxJoueursException("Max joueurs atteint!");
				}
				return gpi.partieDao.ajouterJoueur(partie, joueur);
			}

			public boolean commencerPartie(Partie partie, GestionPartiesImpl gpi) {
				partie.setEtat(EN_COURS);
				return true;
			}
		},
		EN_COURS {

		},
		FINI {
			public Joueur afficherVainqueur(Partie partie, GestionPartiesImpl gpi) {
				return gpi.afficherVainqueur(partie);
			}
		};

		boolean ajouterJoueur(Joueur joueur, Partie partie, GestionPartiesImpl gpi) throws MaxJoueursException {
			return false;
		}

		public boolean commencerPartie(Partie partie, GestionPartiesImpl gpi) {
			return false;
		}

		public Joueur afficherVainqueur(Partie partie, GestionPartiesImpl gpi) {
			return null;
		}
	}

	@Override
	public Partie creerPartie(String nom, Joueur createur) throws PartieDejaEnCoursException, MaxJoueursException {
		try {
			Util.checkString(nom);
			Util.checkObject(createur);
		} catch (Exception e) {
			return null;
		}
		List<Partie> liste = partieDao.lister();
		if (liste.get(liste.size()).getEtat() == Etat.EN_COURS) {
			throw new PartieDejaEnCoursException("Impossible de créer une partie, une autre est déjà en cours");
		}
		Partie partie = partieDao.creerPartie(nom, createur);
		partie.getEtat().ajouterJoueur(createur, partie, this);
		return partie;
	}

	@Override
	public Partie rechercherPartie(String nom) {
		Util.checkString(nom);
		return partieDao.rechercher(nom);
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
	public boolean ajouterJoueur(Partie partie, Joueur joueur) throws MaxJoueursException {
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

	@Override
	public List<Joueur> listeJoueursPartie(Partie partie) {
		List<JoueurPartie> liste = partieDao.listerJoueursPartie(partie);
		List<Joueur> joueurs = new ArrayList<>();
		for (JoueurPartie joueurPartie : liste) {
			Joueur joueur = joueurDaoImpl.rechercher(joueurPartie.getJoueurId());
			joueurs.add(joueur);
		}
		return joueurs;
	}

	@Override
	public boolean ajouterJoueur(Joueur joueur) {
		return false;
	}

}
