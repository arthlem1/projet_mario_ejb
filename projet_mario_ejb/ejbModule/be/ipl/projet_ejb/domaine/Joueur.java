package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import be.ipl.projet_ejb.util.Util;

@Entity
@Table(name = "JOUEURS", schema = "mario_ejb", indexes = { @Index(columnList = "pseudo", unique = true) })
public class Joueur implements Serializable {
	/**
	 * Serial number generated automatically
	 */
	private static final long serialVersionUID = 5964624766458918698L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOUEUR_ID")
	private int id;
	@NotNull
	private String prenom;
	@NotNull
	private String pseudo;
	@NotNull
	private String mdp;
	
	@OneToMany(mappedBy = "joueur", fetch = FetchType.EAGER)
	private List<JoueurPartie> joueurParties = new ArrayList<>();
	
	protected Joueur() {

	}

	public Joueur(String prenom, String pseudo, String mdp) {
		super();
		Util.checkString(prenom);
		Util.checkString(pseudo);
		Util.checkString(mdp);
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.mdp = mdp;
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
		Joueur other = (Joueur) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getPrenom() {
		return prenom;
	}
	
	public String getPseudo() {
		return pseudo;
	}
 
	public String getMdp() {
		return mdp;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", prenom=" + prenom + ", pseudo=" + pseudo + ", joueurParties=" + joueurParties
				+ "]";
	}
	
	
}