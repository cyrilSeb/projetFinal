package com.exemple.model;

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

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Cursus")
public class Cursus {
	@Id
	@SequenceGenerator(name = "seqCursus", sequenceName = "seq_cursus", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCursus")
	@Column(name = "Cursus_id")
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Column(name = "Cursus_nom")
	@JsonView(JsonViews.Common.class)
	private String nom;
	@Column(name = "Cursus_dates")
	@JsonView(JsonViews.Common.class)
	private Date[] dates;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_gestionnaire")
	@JsonView(JsonViews.Cursus.class)
	private Gestionnaire gestionnaire;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_referent")
	@JsonView(JsonViews.Cursus.class)
	private Formateur referent;
	@OneToMany(mappedBy = "cursus")
	@JsonView(JsonViews.Cursus.class)
	private Set<Module> modules;
	@OneToOne(mappedBy = "Projecteur_cursus")
	@JsonView(JsonViews.Cursus.class)
	private Projecteur projecteur;
	@OneToMany(mappedBy = "cursus")
	@JsonView(JsonViews.Cursus.class)
	private Set<Stagiaire> stagiaires;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cursus_salle")
	@JsonView(JsonViews.Cursus.class)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cursus other = (Cursus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
