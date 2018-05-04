package com.exemple.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Ordinateur")
public class Ordinateur extends Materiel {
	@Column(name = "Ordinateur_processeur")
	@JsonView(JsonViews.Common.class)
	private String processeur;
	@Column(name = "Ordinateur_RAM")
	@JsonView(JsonViews.Common.class)
	private Integer ram;
	@Column(name = "Ordinateur_capacite_DD")
	@JsonView(JsonViews.Common.class)
	private Integer DD;
	@Column(name = "Ordinateur_annee_d_achat")
	@JsonView(JsonViews.Common.class)
	private Date anneeAchat;
	@OneToOne
	@JoinColumn(name = "Ordinateur_stagiaire")
	@JsonView(JsonViews.Materiel.class)
	private Stagiaire stagiaire;

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}

	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public Integer getDD() {
		return DD;
	}

	public void setDD(Integer dD) {
		DD = dD;
	}

	public Date getAnneeAchat() {
		return anneeAchat;
	}

	public void setAnneeAchat(Date anneeAchat) {
		this.anneeAchat = anneeAchat;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
}
