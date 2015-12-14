package be.ipl.projet_ejb.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import be.ipl.projet_ejb.util.Util;

@Entity
@Table(name = "DES", schema = "mario_ejb")
@SuppressWarnings("serial")
public class De implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DE_ID")
	private int id;

	public enum Face {
		MARIO, PRENDRE_CARTE, DONNER_DE;
	}

	@NotNull
	@Enumerated
	private Face valeur = Face.MARIO;

	protected De() {
	}

	public De(Face valeur) {
		super();
		Util.checkObject(valeur);
		this.valeur = valeur;
	}

	public int getId() {
		return id;
	}

	public Face getValeur() {
		return valeur;
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
		De other = (De) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
