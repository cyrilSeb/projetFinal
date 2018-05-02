package model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Formateur")
public class Formateur extends User {
	private Date[] disponibilites;
	@OneToMany
	private List<Competence> competences;
	@OneToMany
	private List<Module> modules;
	@OneToOne
	private Cursus cursus;

	public Date[] getDisponibilite() {
		return disponibilites;
	}

	public void setDisponibilite(Date[] disponibilite) {
		this.disponibilites = disponibilite;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
}
