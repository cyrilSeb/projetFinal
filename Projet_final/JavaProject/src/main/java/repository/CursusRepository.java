package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cursus;

public interface CursusRepository extends JpaRepository<Cursus, Long> {

}
