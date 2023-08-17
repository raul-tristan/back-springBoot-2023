package com.backend.service;

import org.springframework.http.ResponseEntity;

import com.backend.model.TipoContribuyente;
import com.backend.response.TipoContribuyenteResponseRest;

public interface ITipoContribuyenteService {
	
	public ResponseEntity<TipoContribuyenteResponseRest> listarTipoContribuyente();
	public ResponseEntity<TipoContribuyenteResponseRest> consultarPorId(Long id);
	public ResponseEntity<TipoContribuyenteResponseRest> crear(TipoContribuyente tipoContribuyente);
	public ResponseEntity<TipoContribuyenteResponseRest> actualizar(TipoContribuyente tipoContribuyente, Long id);
	public ResponseEntity<TipoContribuyenteResponseRest> eliminar(Long id);
}
