package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class FormateurMatierePK implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	private Formateur formateur;
	@ManyToOne(fetch = FetchType.LAZY)
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
