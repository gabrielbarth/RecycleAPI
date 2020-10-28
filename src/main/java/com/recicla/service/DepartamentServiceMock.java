package com.recicla.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recicla.domain.Departament;
import com.recicla.domain.dto.DepartamentDTO;
import com.recicla.repository.IDepartamentRepository;

@Service
@Transactional(readOnly = false)
public class DepartamentServiceMock implements IDepartamentService {

	@Autowired
	private IDepartamentRepository repository;

	@Override
	public DepartamentDTO save(Departament d) {
		return DepartamentDTO.create(repository.save(d));
	}

	@Override
	public DepartamentDTO update(Departament d) {
		return DepartamentDTO.create(repository.save(d));
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartamentDTO> getAllDepartaments() {
		return repository.findAll().stream().map(DepartamentDTO::create).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DepartamentDTO> getDepartamentById(Long id) {
		return repository.findById(id).map(DepartamentDTO::create);
	}

}
