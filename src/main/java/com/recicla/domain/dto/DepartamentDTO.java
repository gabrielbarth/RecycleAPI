package com.recicla.domain.dto;

import org.modelmapper.ModelMapper;

import com.recicla.domain.Departament;

public class DepartamentDTO {

	private Long id;
	private String name;

	public static DepartamentDTO create(Departament d) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(d, DepartamentDTO.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
