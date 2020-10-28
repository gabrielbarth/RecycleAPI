package com.recicla.service;

import java.util.List;
import java.util.Optional;

import com.recicla.domain.Manager;
import com.recicla.domain.dto.ManagerDTO;

public interface IManagerService {
	// operações CRUD
	ManagerDTO save(Manager m);

	ManagerDTO update(Manager m);

	void remove(Long id);

	List<ManagerDTO> getAllManagers();

	Optional<ManagerDTO> getManagerById(Long id);

	// regras de negócio
	ManagerDTO auth(String email, String password);

	void validateEmail(String email);
}
