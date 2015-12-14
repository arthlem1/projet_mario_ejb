package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
	private boolean clockwise;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dateHeureCreation;

	@Transient
	private List<Joueur> listeJoueurs = new ArrayList<>();;

	@ManyToOne
	@JoinColumn(name = "VAINQUEUR")
	private Joueur vainqueur;

	@OneToMany
	@JoinTable(name = "PARTIE_CARTE", schema = "mario_ejb", joinColumns = {
			@JoinColumn(name = "PARTIE_ID") }, inverseJoinColumns = { @JoinColumn(name = "CARTE_ID") })
	private List<Carte> pioche = new ArrayList<>();

	protected Partie() {
		super();
	}

	public Partie(String nom, Joueur joueur) {
		Util.checkString(nom);
		this.nom = nom;
		this.clockwise = true;
		this.dateHeureCreation = Calendar.getInstance();
		listeJoueurs.add(joueur);
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

	public List<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public List<Carte> getPioche() {
		return pioche;
	}

}
