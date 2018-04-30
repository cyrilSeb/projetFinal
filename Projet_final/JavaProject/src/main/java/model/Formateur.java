package model;

import java.util.Date;
import java.util.List;

public class Formateur extends User {
	private List<Date[]> disponibilite;
	private List<Competence> competences;
	private List<Module> modules;
	private Cursus cursus;

	public List<Date[]> getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(List<Date[]> disponibilite) {
		this.disponibilite = disponibilite;
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
