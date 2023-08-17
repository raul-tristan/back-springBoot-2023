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

import com.backend.model.TipoDocumento;
import com.backend.response.TipoDocumentoResponseRest;
import com.backend.service.ITipoDocumentoService;

@RestController
@RequestMapping("/v1")
public class TipoDocumentoRestControlle {
	
	@Autowired
	private ITipoDocumentoService service;
	
	@GetMapping("/tipo_documentos")
	public ResponseEntity<TipoDocumentoResponseRest> consultarTipoDoc() {
		ResponseEntity<TipoDocumentoResponseRest> response = service.listarTipoDocumento();
		return response;
	}
	
	@GetMapping("/tipo_documentos/{id}")
	public ResponseEntity<TipoDocumentoResponseRest> consultarPorId(@PathVariable Long id) {
		ResponseEntity<TipoDocumentoResponseRest> response = service.consultarPorId(id);
		return response;
	}
	
	@PostMapping("/tipo_documentos")
	public ResponseEntity<TipoDocumentoResponseRest> crear(@RequestBody TipoDocumento request) {
		ResponseEntity<TipoDocumentoResponseRest> response = service.crear(request);
		return response;
	}
	
	@PutMapping("/tipo_documentos/{id}") 
	public ResponseEntity<TipoDocumentoResponseRest> actualizar(@RequestBody TipoDocumento request, @PathVariable Long id) {
		ResponseEntity<TipoDocumentoResponseRest> response = service.actualizar(request, id);
		return response;
	}
	
	@DeleteMapping("/tipo_documentos/{id}")
	public ResponseEntity<TipoDocumentoResponseRest> eliminar(@PathVariable Long id) {
		ResponseEntity<TipoDocumentoResponseRest> response = service.eliminar(id);
		return response;
	}
	
	
}
