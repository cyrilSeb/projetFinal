package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Competence {
	@Id
	private FormateurMatierePK key;
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
