package model;

import java.util.List;

public class Gestionnaire extends User {
	private List<Cursus> cursus;

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}
}
