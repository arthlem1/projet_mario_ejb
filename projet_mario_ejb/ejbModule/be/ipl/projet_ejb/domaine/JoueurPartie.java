package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "JOUEUR_PARTIE", schema = "mario_ejb")

public class JoueurPartie implements Serializable {

	@EmbeddedId
	@Column(name = "JOUEUR_PARTIE_ID")
	private JoueurPartiePK joueurPartiePK;

	@NotNull
	@Min(1)
	@Max(6)
	private int ordreJoueurs;

	@ManyToMany
	@JoinTable(name = "JOUEUR_PARTIE_DES", schema = "mario_ejb",
			inverseJoinColumns={@JoinColumn(name="MAIN_DE_ID")})
	private List<De> mainsDe;
	@ManyToMany
	@JoinTable(name="JOUEUR_PARTIE_CARTES",schema = "mario_ejb",
			inverseJoinColumns={@JoinColumn(name="MAIN_CARTE_ID")})
	private List<Carte> mainsCarte;

	protected JoueurPartie() {
		super();
	}

	public JoueurPartie(Joueur joueur, Partie partie, int ordreJoueurs) {
		super();
		this.joueurPartiePK = new JoueurPartiePK(joueur.getId(), partie.getId());
		this.ordreJoueurs = ordreJoueurs;
		this.mainsDe = new ArrayList<>();
		this.mainsCarte = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((joueurPartiePK == null) ? 0 : joueurPartiePK.hashCode());
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
		if (joueurPartiePK == null) {
			if (other.joueurPartiePK != null)
				return false;
		} else if (!joueurPartiePK.equals(other.joueurPartiePK))
			return false;
		return true;
	}

	public JoueurPartiePK getJoueurPartiePK() {
		return joueurPartiePK;
	}

	public int getJoueurId() {
		return joueurPartiePK.getJoueur_id();
	}

	public int getPartieId() {
		return joueurPartiePK.getPartie_id();
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

}
