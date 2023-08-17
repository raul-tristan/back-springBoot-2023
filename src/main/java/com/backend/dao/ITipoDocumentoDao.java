package com.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.backend.model.TipoDocumento;

public interface ITipoDocumentoDao extends CrudRepository<TipoDocumento, Long> {

}
