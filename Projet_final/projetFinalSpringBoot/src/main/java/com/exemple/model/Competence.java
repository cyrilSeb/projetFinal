package com.exemple.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="Competence")
public class Competence {
	@EmbeddedId
	private FormateurMatierePK key;
	@Column(name = "competence_niveau")
	@Enumerated(EnumType.STRING)
	private Niveau niveau;
	@Version
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public FormateurMatierePK getKey() {
		return key;
	}

	public void setKey(FormateurMatierePK key) {
		this.key = key;
	}
}
