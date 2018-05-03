package com.exemple.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Module")
public class Module {
	@Id
	@SequenceGenerator(name = "seqMateriel", sequenceName = "seq_materiel", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMateriel")
	@Column(name = "Module_id")
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Column(name = "Module_dates")
	@JsonView(JsonViews.Common.class)
	private Date[] dates;
	@OneToOne
	@JsonView(JsonViews.Module.class)
	private Matiere matiere;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView(JsonViews.Module.class)
	private Formateur formateur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Module_cursus")
	@JsonView(JsonViews.Module.class)
	private Cursus cursus;
	@Version
	private int version;

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
}
