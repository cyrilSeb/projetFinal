package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Video_projecteur")
public class Projecteur extends Materiel {
	@OneToOne
	@JoinColumn(name="Projecteur_cursus")
	@JsonView(JsonViews.ProjoWithCursus.class)
	private Cursus cursus;

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
}
