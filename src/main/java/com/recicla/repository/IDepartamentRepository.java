package com.recicla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla.domain.Departament;

public interface IDepartamentRepository extends JpaRepository<Departament, Long> {

}
