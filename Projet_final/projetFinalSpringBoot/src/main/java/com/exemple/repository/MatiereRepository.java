package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.model.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
