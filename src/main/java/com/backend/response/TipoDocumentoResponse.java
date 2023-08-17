package com.backend.response;

import java.util.List;

import com.backend.model.TipoDocumento;

public class TipoDocumentoResponse {
	
	private List<TipoDocumento> tipoDocumento;

	public List<TipoDocumento> getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(List<TipoDocumento> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
}
