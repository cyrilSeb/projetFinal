package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{

}
