package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Entidad;
import com.backend.response.EntidadResponseRest;
import com.backend.service.IEntidadService;

@RestController
@RequestMapping("/v1")
public class EntidadRestController {

	@Autowired
	private IEntidadService service;
	
	@GetMapping("/entidades")
	public ResponseEntity<EntidadResponseRest> consultarEnt() {
		
		ResponseEntity<EntidadResponseRest> response = service.buscarEntidad();
		return response;
	}
	
	@GetMapping("/entidades/{id}")
	public ResponseEntity<EntidadResponseRest> consultarPorId(@PathVariable Long id) {
		ResponseEntity<EntidadResponseRest> response = service.consultarPorId(id);
		return response;
	}
	
	@PostMapping("/entidades")
	public ResponseEntity<EntidadResponseRest> crear(@RequestBody Entidad request) {
		ResponseEntity<EntidadResponseRest> response = service.crear(request);
		return response;
	}
	
	@PutMapping("/entidades/{id}")
	public ResponseEntity<EntidadResponseRest> actualizar(@RequestBody Entidad request, @PathVariable Long id) {
		ResponseEntity<EntidadResponseRest> response = service.actualizar(request, id);
		return response;
	}
	
	@DeleteMapping("/entidades/{id}")
	public ResponseEntity<EntidadResponseRest> eliminar(@PathVariable Long id) {
		ResponseEntity<EntidadResponseRest> response = service.eliminar(id);
		return response;
	}
}
