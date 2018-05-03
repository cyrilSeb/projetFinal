package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordonnees {
	@Column(name = "coordonnees_telephone")
	private String telephone;
	@Column(name = "coordonnees_email")
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
