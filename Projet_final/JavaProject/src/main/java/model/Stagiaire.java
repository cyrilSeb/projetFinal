package model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Stagiaire")
public class Stagiaire extends User {
	private Ordinateur ordinateur;
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
