package com.exemple.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Coordonnees {
	@Column(name = "coordonnees_telephone")
	@JsonView(JsonViews.Common.class)
	private String telephone;
	@Column(name = "coordonnees_email")
	@JsonView(JsonViews.Common.class)
	private String email;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
