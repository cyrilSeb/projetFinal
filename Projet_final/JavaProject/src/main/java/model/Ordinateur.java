package model;

import java.util.Date;

public class Ordinateur extends Materiel {
	private String processeur;
	private Integer ram;
	private Integer DD;
	private Date anneeAchat;

	private Stagiaire stagiaire;

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}

	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public Integer getDD() {
		return DD;
	}

	public void setDD(Integer dD) {
		DD = dD;
	}

	public Date getAnneeAchat() {
		return anneeAchat;
	}

	public void setAnneeAchat(Date anneeAchat) {
		this.anneeAchat = anneeAchat;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
}
