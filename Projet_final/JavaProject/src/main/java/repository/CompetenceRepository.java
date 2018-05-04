package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Competence;
import model.FormateurMatierePK;

public interface CompetenceRepository extends JpaRepository<Competence, FormateurMatierePK> {
}
