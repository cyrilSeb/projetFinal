package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FormateurMatierePK implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formateur_id")
	private Formateur formateur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
}
