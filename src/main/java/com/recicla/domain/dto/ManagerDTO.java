package com.recicla.domain.dto;

import org.modelmapper.ModelMapper;

import com.recicla.domain.Departament;
import com.recicla.domain.Manager;

public class ManagerDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String occupation;
	private Departament departament;

	public static ManagerDTO create(Manager m) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(m, ManagerDTO.class);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

}
