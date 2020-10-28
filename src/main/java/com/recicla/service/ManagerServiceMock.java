package com.recicla.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.recicla.domain.Manager;
import com.recicla.domain.dto.ManagerDTO;
import com.recicla.exception.ValidateBusinessRulesException;
import com.recicla.repository.IManagerRepository;

public class ManagerServiceMock implements IManagerService {

	@Autowired
	private IManagerRepository repository;

	@Override
	public ManagerDTO save(Manager m) {
		validateEmail(m.getEmail());
		return ManagerDTO.create(repository.save(m));
	}

	@Override
	public ManagerDTO update(Manager m) {
		validateEmail(m.getEmail());
		return ManagerDTO.create(repository.save(m));
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ManagerDTO> getAllManagers() {
		return repository.findAll().stream().map(ManagerDTO::create).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ManagerDTO> getManagerById(Long id) {
		return repository.findById(id).map(ManagerDTO::create);
	}

	// Regras de negócio
	@Override
	public ManagerDTO auth(String email, String password) {
		Optional<Manager> manager = repository.findByEmail(email);
		if (!manager.isPresent()) {
			throw new ValidateBusinessRulesException("User email was not found.");
		}
		if (!manager.get().getPassword().equals(password)) {
			throw new ValidateBusinessRulesException("Wrong password.");
		}
		return ManagerDTO.create(manager.get());
	}

	@Override
	public void validateEmail(String email) {
		boolean exist = repository.existsByEmail(email);

		if (exist) {
			throw new ValidateBusinessRulesException("This email already exists.");
		}
	}

}
