package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Salle")
public class Salle {
	@Id
	@SequenceGenerator(name = "seqMateriel", sequenceName = "seq_materiel", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMateriel")
	@Column(name = "Salle_id")
	private Long id;
	@Column(name = "Salle_capacite")
	private Integer capacite;
	@Column(name = "Salle_numero")
	private String numero;
	@Embedded
	private Adresse adresse;
	@OneToMany(mappedBy = "salle")
	private Set<Cursus> cursus;
	@Version
	private int version;

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(Set<Cursus> cursus) {
		this.cursus = cursus;
	}
}
