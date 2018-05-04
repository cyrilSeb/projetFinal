package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@Column(name = "adresse_numero")
	@JsonView(JsonViews.Common.class)
	private Integer numero;
	@Column(name = "adresse_rue")
	@JsonView(JsonViews.Common.class)
	private String rue;
	@Column(name = "adresse_code_postal")
	@JsonView(JsonViews.Common.class)
	private String codePostal;
	@Column(name = "adresse_ville")
	@JsonView(JsonViews.Common.class)
	private String ville;
	@Column(name = "adresse_pays")
	@JsonView(JsonViews.Common.class)
	private String pays;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
}
