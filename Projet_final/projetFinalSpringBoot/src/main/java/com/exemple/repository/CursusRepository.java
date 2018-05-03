package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.model.Cursus;

public interface CursusRepository extends JpaRepository<Cursus, String>{

}
