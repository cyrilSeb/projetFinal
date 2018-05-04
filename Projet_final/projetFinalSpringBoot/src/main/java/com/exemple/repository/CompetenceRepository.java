package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.model.Competence;
import com.exemple.model.FormateurMatierePK;

public interface CompetenceRepository extends JpaRepository<Competence, FormateurMatierePK> {
}
