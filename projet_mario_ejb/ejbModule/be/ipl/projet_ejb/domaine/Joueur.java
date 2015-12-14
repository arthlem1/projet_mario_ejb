package be.ipl.projet_ejb.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
	@Column
	private String pseudo;
	@NotNull
	@Column
	private String mdp;

	protected Joueur() {

	}

	public Joueur(String pseudo, String mdp) {
		super();
		Util.checkString(pseudo);
		Util.checkString(mdp);
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

	public String getPseudo() {
		return pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public int getId() {
		return id;
	}

}