package model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Formateur")
public class Formateur extends User {
	private List<Date[]> disponibilites;
	private List<Competence> competences;
	private List<Module> modules;
	private Cursus cursus;

	public List<Date[]> getDisponibilite() {
		return disponibilites;
	}

	public void setDisponibilite(List<Date[]> disponibilite) {
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
