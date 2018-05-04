package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Formateur")
public class Formateur extends User {
	@Column(name = "Formateur_disponibilites")
	@JsonView(JsonViews.Common.class)
	private Date[] disponibilites;
	@OneToMany(mappedBy = "key.formateur")
	@JsonView(JsonViews.User.class)
	private Set<Competence> competences;
	@OneToMany(mappedBy = "formateur")
	@JsonView(JsonViews.User.class)
	private Set<Module> modules;
	@OneToMany(mappedBy = "referent")
	@JsonView(JsonViews.User.class)
	private Set<Cursus> setCursus;

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

	public Set<Cursus> getCursus() {
		return setCursus;
	}

	public void setCursus(Set<Cursus> setCursus) {
		this.setCursus = setCursus;
	}
}
