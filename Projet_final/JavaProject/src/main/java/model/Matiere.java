package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Matiere")
public class Matiere {
	@Id
	@SequenceGenerator(name = "seqMateriel", sequenceName = "seq_materiel", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMateriel")
	@Column(name = "Matiere_id")
	private Long id;
	@Column(name = "Matiere_titre")
	private String titre;
	@Column(name = "Matiere_nombre_d_heures")
	private Integer nbHeure;
	@Column(name = "Matiere_objectifs")
	private String Objectifs;
	@OneToMany
	private Set<Matiere> prerequis;
	@Column(name = "Matiere_contenu")
	private String contenu;
	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getNbHeure() {
		return nbHeure;
	}

	public void setNbHeure(Integer nbHeure) {
		this.nbHeure = nbHeure;
	}

	public String getObjectifs() {
		return Objectifs;
	}

	public void setObjectifs(String objectifs) {
		Objectifs = objectifs;
	}

	public Set<Matiere> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(Set<Matiere> prerequis) {
		this.prerequis = prerequis;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
