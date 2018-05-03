package com.exemple.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;




@Entity
@Table(name="user_roles")
public class UserRole { //classe indispensable pour spring security, respecter ce schema la

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private int id;
	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.Common.class)
	private Role role;
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
