package be.ipl.projet_ejb.usecasesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.projet_ejb.daoimpl.JoueurDaoImpl;
import be.ipl.projet_ejb.daoimpl.JoueurPartieDaoImpl;
import be.ipl.projet_ejb.daoimpl.PartieDaoImpl;
import be.ipl.projet_ejb.domaine.Carte;
import be.ipl.projet_ejb.domaine.InitDB;
import be.ipl.projet_ejb.domaine.Joueur;
import be.ipl.projet_ejb.domaine.JoueurPartie;
import be.ipl.projet_ejb.domaine.Partie;
import be.ipl.projet_ejb.exceptions.MaxJoueursException;
import be.ipl.projet_ejb.exceptions.PartieDejaEnCoursException;
import be.ipl.projet_ejb.exceptions.PasAssezDeJoueursException;
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
	@EJB
	private InitDB initDB;

	public enum Etat {
		INITIAL {
			boolean ajouterJoueur(Joueur joueur, Partie partie, GestionPartiesImpl gpi) throws MaxJoueursException {
				Util.checkObject(joueur);
				Util.checkObject(partie);
				JoueurPartie jp;
				try {
					jp = new JoueurPartie(joueur, partie, 1);
					System.out.println("ID JOUEUR " + jp.getJoueur().getId() + " ID PARTIE " + jp.getPartie().getId());
					gpi.joueurPartieDaoImpl.enregistrer(jp);
				} catch (Exception e) {
					e.printStackTrace();
					throw new MaxJoueursException("Max joueurs atteint!");
				}
				Util.checkObject(joueur);
				Util.checkObject(partie);
				return gpi.partieDao.ajouterJoueur(partie, jp);
			}

			public boolean commencerPartie(Partie partie, GestionPartiesImpl gpi) throws PasAssezDeJoueursException {
				partie = gpi.rechercherPartie(partie.getNom());
				if (partie.getListeJoueurs().size() == 1) {
					partie.setEtat(FINI);
					throw new PasAssezDeJoueursException("Pas assez de joueurs dans la partie.");
				}
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

		public boolean commencerPartie(Partie partie, GestionPartiesImpl gpi) throws PasAssezDeJoueursException {
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
		Partie partie = partieDao.rechercher(nom);
		if (partie != null && partie.getEtat() == Etat.EN_COURS) {
			throw new PartieDejaEnCoursException("Impossible de cr�er une partie, une autre est d�j� en cours");
		} else if (partie.getEtat() == Etat.FINI)
			partie = partieDao.creerPartie(nom, createur);
		else {
			System.out.println("ID PARTIE " + partie.getId());
			partie.getEtat().ajouterJoueur(createur, partie, this);
		}
		partie = partieDao.mettreAJour(partie);
		return partie;
	}

	@Override
	public Partie rechercherPartie(String nom) {
		Util.checkString(nom);
		return partieDao.rechercher(nom);
	}

	@Override
	public Partie joueurSuivant(Partie partie) {
		Util.checkObject(partie);
		return partieDao.passerAuJoueurSuivant(partie);
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
		Util.checkObject(partie);
		Util.checkObject(joueur);
		return partie.getEtat().ajouterJoueur(joueur, partie, this);
	}

	@Override
	public boolean commencerPartie(Partie partie) throws PasAssezDeJoueursException {
		Util.checkObject(partie);
		return partie.getEtat().commencerPartie(partie, this);
	}

	@Override
	public Joueur afficherVainqueur(Partie partie) {
		Util.checkObject(partie);
		return partie.getEtat().afficherVainqueur(partie, this);
	}

	@Override
	public List<Joueur> listeJoueursPartie(Partie partie) {
		Util.checkObject(partie);
		List<JoueurPartie> liste = partieDao.listerJoueursPartie(partie);
		List<Joueur> joueurs = new ArrayList<>();
		for (JoueurPartie joueurPartie : liste) {
			Joueur joueur = joueurDaoImpl.rechercher(joueurPartie.getJoueur().getId());
			joueurs.add(joueur);
		}
		return joueurs;
	}

	@Override
	public Partie getPartieEnCours() {
		return partieDao.getPartieEnCours();
	}

	@Override
	public Partie initialiserMainsCartes(Partie partie) {
		partie = partieDao.recharger(partie.getNom());
		List<Carte> cartes = initDB.getWazabi().getCarte();
		List<JoueurPartie> joueurs = partieDao.listerJoueursPartie(partie);
		for (JoueurPartie joueurPartie : joueurs) {
			for (int i = 0; i < 3; i++) {
				Random random = new Random();
				Carte carte = cartes.remove(random.nextInt(cartes.size()) + 1);
				joueurPartie.getMainsCarte().add(carte);
			}
		}
		return partieDao.mettreAJour(partie);
	}

	@Override
	public Partie initialiserPioche(Partie partie) {
		partie = partieDao.recharger(partie.getNom());
		List<Carte> pioche = partie.getPioche();
		List<Carte> cartes = initDB.getWazabi().getCarte();
		pioche.addAll(cartes);
		return partieDao.mettreAJour(partie);
	}

	@Override
	public Partie getPartieInitiale() {
		return partieDao.getPartieInitiale();
	}

}
