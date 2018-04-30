package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Matiere {
	@Id
	@SequenceGenerator(name = "seqMateriel", sequenceName = "seq_materiel", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMateriel")
	private Long id;
	private String titre;
	private Integer nbHeure;
	private String Objectifs;
	private List<Matiere> prerequis;
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

	public List<Matiere> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(List<Matiere> prerequis) {
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
