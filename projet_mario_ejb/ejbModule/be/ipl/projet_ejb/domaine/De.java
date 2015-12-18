package be.ipl.projet_ejb.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}face" maxOccurs="6"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nbParJoueur" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nbTotalDes" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = { "face" })
@XmlType(name="")
@XmlRootElement(name = "de")
@Entity
@Table(name = "DES", schema = "mario_ejb")
@SuppressWarnings("serial")
public class De implements Serializable {
	@XmlTransient
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DE_ID")
	private int id;

	// @XmlTransient
	// public enum Face {
	// MARIO, PRENDRE_CARTE, DONNER_DE;
	// }
	//
	// @XmlTransient
	// @NotNull
	// @Enumerated
	// private Face valeur = Face.MARIO;
	//
	@Transient
	@XmlAttribute(name = "nbParJoueur", required = true)
	protected int nbParJoueur;
	@Transient
	@XmlAttribute(name = "nbTotalDes", required = true)
	protected int nbTotalDes;
	@XmlTransient
	private String valeur ;
	protected De() {
	}

	// public De(Face valeur) {
	// super();
	// Util.checkObject(valeur);
	// this.valeur = valeur;
	// }

	public int getId() {
		return id;
	}

	// public Face getValeur() {
	// return valeur;
	// }

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
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

	/**
	 * Gets the value of the face property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the face property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFace().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Face }
	 * 
	 * 
	 */

	/**
	 * Gets the value of the nbParJoueur property.
	 * 
	 */
	public int getNbParJoueur() {
		return nbParJoueur;
	}

	/**
	 * Sets the value of the nbParJoueur property.
	 * 
	 */
	public void setNbParJoueur(int value) {
		this.nbParJoueur = value;
	}

	/**
	 * Gets the value of the nbTotalDes property.
	 * 
	 */
	public int getNbTotalDes() {
		return nbTotalDes;
	}

	/**
	 * Sets the value of the nbTotalDes property.
	 * 
	 */
	public void setNbTotalDes(int value) {
		this.nbTotalDes = value;
	}

	@Override
	public String toString() {
		return "De [id=" + id + ", valeur=" + valeur + "]";
	}
	
	

}
