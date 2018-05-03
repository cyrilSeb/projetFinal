package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Cursus")
public class Cursus {
	@Id
	@SequenceGenerator(name = "seqCursus", sequenceName = "seq_cursus", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCursus")
	@Column(name = "Cursus_id")
	private Long id;
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
	private Set<Module> modules;
	@OneToOne
	private Projecteur projecteur;
	@OneToMany(mappedBy = "cursus")
	private Set<Stagiaire> stagiaires;
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

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Projecteur getProjecteur() {
		return projecteur;
	}

	public void setProjecteur(Projecteur projecteur) {
		this.projecteur = projecteur;
	}

	public Set<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(Set<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
}
