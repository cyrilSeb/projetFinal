package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);

}