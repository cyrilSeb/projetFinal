package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Cursus {
	@Id
	@Column(name = "Cursus_nom")
	private String nom;
	@Column(name = "Cursus_dates")
	private Date[] dates;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_gestionnaire")
	private Gestionnaire gestionnaire;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_referent")
	private Formateur referent;
	@OneToMany(mappedBy = "cursus")
	private List<Module> modules;
	@OneToOne
	private Projecteur projecteur;
	@OneToMany(mappedBy = "cursus")
	private List<Stagiaire> stagiaires;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_salle")
	private Salle salle;
	@Version
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Projecteur getProjecteur() {
		return projecteur;
	}

	public void setProjecteur(Projecteur projecteur) {
		this.projecteur = projecteur;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
}
