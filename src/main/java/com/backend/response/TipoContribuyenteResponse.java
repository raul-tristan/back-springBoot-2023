package com.backend.response;

import java.util.List;

import com.backend.model.TipoContribuyente;

public class TipoContribuyenteResponse {

	private List<TipoContribuyente> tipoContribuyente;

	public List<TipoContribuyente> getTipoContribuyente() {
		return tipoContribuyente;
	}

	public void setTipoContribuyente(List<TipoContribuyente> tipoContribuyente) {
		this.tipoContribuyente = tipoContribuyente;
	}

}
