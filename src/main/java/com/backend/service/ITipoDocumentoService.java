package com.backend.service;

import org.springframework.http.ResponseEntity;

import com.backend.model.TipoDocumento;
import com.backend.response.TipoDocumentoResponseRest;

public interface ITipoDocumentoService {
	
	public ResponseEntity<TipoDocumentoResponseRest> listarTipoDocumento();
	public ResponseEntity<TipoDocumentoResponseRest> consultarPorId(Long id);
	public ResponseEntity<TipoDocumentoResponseRest> crear(TipoDocumento tipoDocumento);
	public ResponseEntity<TipoDocumentoResponseRest> actualizar(TipoDocumento tipoDocumento, Long id);
	public ResponseEntity<TipoDocumentoResponseRest> eliminar(Long id);
}
