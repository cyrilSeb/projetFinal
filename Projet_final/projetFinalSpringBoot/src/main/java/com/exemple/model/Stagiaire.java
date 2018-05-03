package com.exemple.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Stagiaire")
public class Stagiaire extends User {
	@OneToOne
	@JsonView(JsonViews.UserWithOrdi.class)
	private Ordinateur ordinateur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Stagiaire_cursus")
	@JsonView(JsonViews.UserWithCursus.class)
	private Cursus cursus;

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
}
