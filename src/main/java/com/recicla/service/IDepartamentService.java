package com.recicla.service;

import java.util.List;
import java.util.Optional;

import com.recicla.domain.Departament;
import com.recicla.domain.dto.DepartamentDTO;

public interface IDepartamentService {

	// operações CRUD
	DepartamentDTO save(Departament d);

	DepartamentDTO update(Departament d);

	void remove(Long id);

	List<DepartamentDTO> getAllDepartaments();

	Optional<DepartamentDTO> getDepartamentById(Long id);

}
