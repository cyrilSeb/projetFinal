package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}
