package be.ipl.projet_ejb.domaine;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Embeddable
public class JoueurPartiePK implements Serializable {
	private int joueur_id;
	private int partie_id;

	protected JoueurPartiePK() {
		super();
	}

	public JoueurPartiePK(int joueur_id, int partie_id) {
		super();
		this.joueur_id = joueur_id;
		this.partie_id = partie_id;
	}

	public int getJoueur_id() {
		return joueur_id;
	}

	public int getPartie_id() {
		return partie_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + joueur_id;
		result = prime * result + partie_id;
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
		JoueurPartiePK other = (JoueurPartiePK) obj;
		if (joueur_id != other.joueur_id)
			return false;
		if (partie_id != other.partie_id)
			return false;
		return true;
	}

}
