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

import com.backend.model.TipoContribuyente;
import com.backend.response.TipoContribuyenteResponseRest;
import com.backend.service.ITipoContribuyenteService;

@RestController
@RequestMapping("/v1")
public class TipoContribuyenteRestController {
	
	@Autowired
	private ITipoContribuyenteService service;
	
	@GetMapping("/tipo_contribuyentes")
	public ResponseEntity<TipoContribuyenteResponseRest> listarTipoContribuyente() {
		ResponseEntity<TipoContribuyenteResponseRest> response = service.listarTipoContribuyente();
		return response;
	}
	
	@GetMapping("/tipo_contribuyentes/{id}")
	public ResponseEntity<TipoContribuyenteResponseRest> consultarPorId(@PathVariable Long id) {
		ResponseEntity<TipoContribuyenteResponseRest> response = service.consultarPorId(id);
		return response;
	}
	
	@PostMapping("/tipo_contribuyentes")
	public ResponseEntity<TipoContribuyenteResponseRest> crear(@RequestBody TipoContribuyente request) {
		ResponseEntity<TipoContribuyenteResponseRest> response = service.crear(request);
		return response;
	}
	
	
	@PutMapping("/tipo_contribuyentes/{id}")
	public ResponseEntity<TipoContribuyenteResponseRest> actualizar(@RequestBody TipoContribuyente request, @PathVariable Long id) {
		ResponseEntity<TipoContribuyenteResponseRest> response = service.actualizar(request, id);
		return response;
	}
	
	@DeleteMapping("/tipo_contribuyentes/{id}")
	public ResponseEntity<TipoContribuyenteResponseRest> eliminar(@PathVariable Long id) {
		ResponseEntity<TipoContribuyenteResponseRest> response = service.eliminar(id);
		return response;
	}
	
	
}
