package com.backend.service;

import org.springframework.http.ResponseEntity;

import com.backend.model.Entidad;
import com.backend.response.EntidadResponseRest;

public interface IEntidadService {
	
	public ResponseEntity<EntidadResponseRest> buscarEntidad();
	public ResponseEntity<EntidadResponseRest> consultarPorId(Long id);
	public ResponseEntity<EntidadResponseRest> crear(Entidad entidad);
	public ResponseEntity<EntidadResponseRest> actualizar(Entidad entidad, Long id);
	public ResponseEntity<EntidadResponseRest> eliminar(Long id);
}
