package model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Stagiaire")
public class Stagiaire extends User {
	@OneToOne
	private Ordinateur ordinateur;
	@OneToMany
	private List<Cursus> cursus;

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}
}
