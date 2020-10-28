package com.recicla.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recicla.domain.Departament;
import com.recicla.domain.dto.DepartamentDTO;
import com.recicla.service.IDepartamentService;

@RestController
@RequestMapping("/api/departaments")
public class DepartamentController {

	@Autowired
	public IDepartamentService service;

	@GetMapping()
	public ResponseEntity<List<DepartamentDTO>> listDepartaments() {
		return ResponseEntity.ok(service.getAllDepartaments());

	}

	@GetMapping("/{id}")
	public ResponseEntity listDepartamentById(@PathVariable("id") Long id) {
		Optional<DepartamentDTO> departament = service.getDepartamentById(id);

		if (!departament.isPresent()) {
			return ResponseEntity.badRequest().body("Department reported by id was not found.");
		} else {
			return ResponseEntity.ok(departament);
		}

	}

	@PostMapping
	public ResponseEntity save(@RequestBody Departament departament) {
		try {
			DepartamentDTO dto = service.save(departament);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Departament departament) {
		return service.getDepartamentById(id).map(entity -> {
			try {
				departament.setId(entity.getId());
				service.update(departament);
				return ResponseEntity.ok(departament);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Departament not found.", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity remove(@PathVariable("id") Long id) {
		return service.getDepartamentById(id).map(entity -> {
			service.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Departament not found.", HttpStatus.BAD_REQUEST));
	}

}
