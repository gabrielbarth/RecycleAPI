package com.recicla.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla.domain.Manager;

public interface IManagerRepository extends JpaRepository<Manager, Long> {

	// string methods queries
	Optional<Manager> findByEmail(String email);

	boolean existsByEmail(String email);
}
