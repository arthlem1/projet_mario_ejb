package be.ipl.projet_ejb.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import be.ipl.projet_ejb.util.Util;
 
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
 *         &lt;element name="figure" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="cout" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nb" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="effet" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codeEffet" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "carte")
@SuppressWarnings("serial")
@Entity
@Table(name = "CARTES", schema = "mario_ejb")
public class Carte implements Serializable, Cloneable {
	@Id 
	@XmlTransient
	@Column(name = "CARTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@XmlAttribute(name = "codeEffet", required = true)
	private int codeEffet;
	@Min(0)
	@XmlAttribute(name = "cout", required = true)
	private int cout;

	@Transient
	@XmlElementRef(name = "figure", type = JAXBElement.class, required = false)
	@XmlMixed
	protected List<Serializable> content;
	@Transient
	@XmlAttribute(name = "nb", required = true)
	protected int nb;
	@Transient
	@XmlAttribute(name = "effet", required = true)
	protected String effet;
	@Transient
	@XmlAttribute(name = "src")
	protected String src;
	
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

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Bloquer l'application? A voir
			e.printStackTrace();
		}
		return null;
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

	/**
	 * Gets the value of the content property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the content property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link Carte.Figure }{@code >} {@link String
	 * }
	 * 
	 * 
	 */
	public List<Serializable> getContent() {
		if (content == null) {
			content = new ArrayList<Serializable>();
		}
		return this.content;
	}

	/**
	 * Sets the value of the cout property.
	 * 
	 */
	public void setCout(int value) {
		this.cout = value;
	}

	/**
	 * Gets the value of the nb property.
	 * 
	 */
	public int getNb() {
		return nb;
	}

	/**
	 * Sets the value of the nb property.
	 * 
	 */
	public void setNb(int value) {
		this.nb = value;
	}

	/**
	 * Gets the value of the effet property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEffet() {
		return effet;
	}

	/**
	 * Sets the value of the effet property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEffet(String value) {
		this.effet = value;
	}

	/**
	 * Sets the value of the codeEffet property.
	 * 
	 */
	public void setCodeEffet(int value) {
		this.codeEffet = value;
	}

	/**
	 * Gets the value of the src property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Sets the value of the src property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSrc(String value) {
		this.src = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Figure {

		@XmlAttribute(name = "ref")
		protected String ref;

		/**
		 * Gets the value of the ref property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRef() {
			return ref;
		}

		/**
		 * Sets the value of the ref property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRef(String value) {
			this.ref = value;
		}

	}

}
