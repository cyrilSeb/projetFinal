package com.exemple.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Gestionnaire")
public class Gestionnaire extends User {
	@OneToMany(mappedBy = "gestionnaire")
	private Set<Cursus> cursus;

	public Set<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(Set<Cursus> cursus) {
		this.cursus = cursus;
	}
}
