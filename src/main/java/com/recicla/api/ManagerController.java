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

import com.recicla.domain.Manager;
import com.recicla.domain.dto.ManagerDTO;
import com.recicla.exception.ValidateBusinessRulesException;
import com.recicla.service.IManagerService;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

	@Autowired
	public IManagerService service;
	

	@GetMapping()
	public ResponseEntity<List<ManagerDTO>> listManagers() {
		return ResponseEntity.ok(service.getAllManagers());
	}

	@GetMapping("/{id}")
	public ResponseEntity listManagerById(@PathVariable("id") Long id) {
		Optional<ManagerDTO> manager = service.getManagerById(id);

		if (!manager.isPresent()) {
			return ResponseEntity.badRequest().body("Manager reported by id was not found.");
		} else {
			return ResponseEntity.ok(manager);
		}

	}

	@PostMapping("/auth")
	public ResponseEntity auth(@RequestBody ManagerDTO manager) {
		try {
			ManagerDTO authenticatedManager = service.auth(manager.getEmail(), manager.getPassword());
			return ResponseEntity.ok(authenticatedManager);
		} catch (ValidateBusinessRulesException error) {
			return ResponseEntity.badRequest().body(error.getMessage());
		}

	}

	@PostMapping
	public ResponseEntity save(@RequestBody Manager manager) {
		try {
			ManagerDTO dto = service.save(manager);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Manager manager) {
		return service.getManagerById(id).map(entity -> {
			try {
				manager.setId(entity.getId());
				service.update(manager);
				return ResponseEntity.ok(manager);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Manager not found.", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity remove(@PathVariable("id") Long id) {
		return service.getManagerById(id).map(entity -> {
			service.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Manager not found.", HttpStatus.BAD_REQUEST));
	}

}