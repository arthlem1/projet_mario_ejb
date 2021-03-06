package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import be.ipl.projet_ejb.usecasesimpl.GestionPartiesImpl;
import be.ipl.projet_ejb.util.Util;

@Entity
@Table(name = "PARTIES", schema = "mario_ejb")
@SuppressWarnings("serial")
public class Partie implements Serializable {
	@Id
	@Column(name = "PARTIE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(unique = true)
	private String nom;

	@NotNull
	private boolean clockwise = true;

	@NotNull
	private boolean started = false;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GestionPartiesImpl.Etat etat = GestionPartiesImpl.Etat.INITIAL;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateHeureCreation;

	@OneToMany(mappedBy = "partie", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<JoueurPartie> listeJoueurs = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "VAINQUEUR")
	private Joueur vainqueur;

	// @NotNull
	// private int nbJoueur=0;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "PARTIE_CARTE", schema = "mario_ejb", joinColumns = { @JoinColumn(name = "PARTIE_ID") })
	private List<Carte> pioche = new ArrayList<>();

	@OneToOne(cascade = { CascadeType.ALL })
	private JoueurPartie joueur_courant;

	protected Partie() {
		super();
	}

	public Partie(String nom, Joueur joueur) {
		Util.checkString(nom);
		this.nom = nom;
		this.dateHeureCreation = Calendar.getInstance();
		listeJoueurs.add(new JoueurPartie(joueur, this, 1));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partie other = (Partie) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// public int getNbJoueur(){
	// return nbJoueur;
	// }

	// public void setNbJoueur(int nb){
	// nbJoueur=nb;
	// }

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public boolean isClockwise() {
		return clockwise;
	}

	public Calendar getDateHeureCreation() {
		return dateHeureCreation;
	}

	public List<JoueurPartie> getListeJoueurs() {
		return listeJoueurs;
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public List<Carte> getPioche() {
		return pioche;
	}

	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}

	public JoueurPartie getJoueur_courant() {
		return joueur_courant;
	}

	public void setJoueur_courant(JoueurPartie joueur_courant) {
		this.joueur_courant = joueur_courant;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public void setEtat(GestionPartiesImpl.Etat etat) {
		this.etat = etat;
	}

	public GestionPartiesImpl.Etat getEtat() {
		return etat;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	@Override
	public String toString() {
		return "Partie [id=" + id + ", nom=" + nom + " ]";
	}
	
	public boolean supprimer(JoueurPartie joueurPartie){
		return this.listeJoueurs.remove(joueurPartie);
	}
}
