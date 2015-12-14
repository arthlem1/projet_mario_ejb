package be.ipl.projet_ejb.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import be.ipl.projet_ejb.util.Util;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARTES", schema = "mario_ejb")
public class Carte implements Serializable {
	@Id
	@Column(name="CARTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int codeEffet;
	@Min(0)
	private int cout;

	protected Carte() {
		super();
	}

	public Carte(int codeEffet, int cout) {
		super();
		Util.checkPositive(codeEffet);
		Util.checkPositive(cout);
		this.codeEffet = codeEffet;
		this.cout = cout;
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
		Carte other = (Carte) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public int getCodeEffet() {
		return codeEffet;
	}

	public int getCout() {
		return cout;
	}

}
