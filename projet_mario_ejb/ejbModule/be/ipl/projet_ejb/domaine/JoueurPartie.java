package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "JOUEUR_PARTIE", schema = "mario_ejb")

public class JoueurPartie implements Serializable {

	/*
	 * @EmbeddedId
	 * 
	 * @Column(name = "JOUEUR_PARTIE_ID") private JoueurPartiePK joueurPartiePK;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOUEUR_PARTIE_ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "JOUEUR_ID")
	private Joueur joueur;

	@ManyToOne
	@JoinColumn(name = "PARTIE_ID")
	private Partie partie;

	@NotNull
	private int blocked;

	@NotNull
	@Min(1)
	@Max(6)
	private int ordreJoueurs;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "JOUEUR_PARTIE_DES", schema = "mario_ejb", inverseJoinColumns = {
			@JoinColumn(name = "MAIN_DE_ID") })
	private List<De> mainsDe = new ArrayList<>();
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "JOUEUR_PARTIE_CARTES", schema = "mario_ejb", inverseJoinColumns = {
			@JoinColumn(name = "MAIN_CARTE_ID") })
	private List<Carte> mainsCarte = new ArrayList<>();

	protected JoueurPartie() {
		super();
	}

	public JoueurPartie(Joueur joueur, Partie partie, int ordreJoueurs) {
		super();
		this.ordreJoueurs = ordreJoueurs;
		this.joueur = joueur;
		this.partie = partie;
		this.blocked = 0;
	}

	public boolean isBlocked() {
		int i = blocked;
		if (blocked > 0) {
			blocked--;
		}
		return i != 0;
	}

	public void setBlocked() {
		blocked++;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Partie getPartie() {
		return partie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		result = prime * result + ((partie == null) ? 0 : partie.hashCode());
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
		JoueurPartie other = (JoueurPartie) obj;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		if (partie == null) {
			if (other.partie != null)
				return false;
		} else if (!partie.equals(other.partie))
			return false;
		return true;
	}

	public int getOrdreJoueurs() {
		return ordreJoueurs;
	}

	public List<De> getMainsDe() {
		return mainsDe;
	}

	public List<Carte> getMainsCarte() {
		return mainsCarte;
	}

	public void setOrdreJoueurs(int ordreJoueurs) {
		this.ordreJoueurs = ordreJoueurs;
	}

	public void setMainsDe(List<De> mainsDe) {
		this.mainsDe = mainsDe;
	}

	public void setMainsCarte(List<Carte> mainsCarte) {
		this.mainsCarte = mainsCarte;
	}

	@Override
	public String toString() {
		return "JoueurPartie [id=" + id + ", joueur=" + joueur + ", partie=" + partie + ", blocked=" + blocked
				+ ", ordreJoueurs=" + ordreJoueurs + "]";
	}

	public int getId() {
		return id;
	}

}
