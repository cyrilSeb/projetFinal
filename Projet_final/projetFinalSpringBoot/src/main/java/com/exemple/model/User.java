package com.exemple.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, length = 20, name = "type")
@Table(name = "users")
public abstract class User {
	@Id
	@SequenceGenerator(name = "seqUser", sequenceName = "seq_user", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
	@Column(name = "user_id")
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Column(name = "user_nom")
	@JsonView(JsonViews.Common.class)
	private String nom;
	@Column(name = "user_prenom")
	@JsonView(JsonViews.Common.class)
	private String prenom;
	@JsonView(JsonViews.Authentification.class)
	private String username;
	@JsonView(JsonViews.Authentification.class)
	private String password;
	@Embedded
	@JsonView(JsonViews.Common.class)
	private Adresse adresse;
	@Embedded
	@JsonView(JsonViews.Common.class)
	private Coordonnees coordonnees;
	@JsonView(JsonViews.Authentification.class)
	private boolean enable;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonView(JsonViews.Authentification.class)
	private Set<UserRole> roles;
	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
	}

	public User(User u) {
		this.username = u.username;
		this.password = u.password;
		this.enable = u.enable;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
