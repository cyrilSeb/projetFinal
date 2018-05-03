package com.exemple.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Formateur")
public class Formateur extends User {
	@Column(name = "Formateur_disponibilites")
	private Date[] disponibilites;
	@OneToMany(mappedBy = "key.formateur")
	private Set<Competence> competences;
	@OneToMany(mappedBy = "formateur")
	private Set<Module> modules;
	@OneToOne
	private Cursus cursus;

	public Date[] getDisponibilite() {
		return disponibilites;
	}

	public void setDisponibilite(Date[] disponibilite) {
		this.disponibilites = disponibilite;
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
}
