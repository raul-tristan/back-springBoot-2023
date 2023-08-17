package com.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.backend.model.Entidad;

public interface IEntidadDao extends CrudRepository<Entidad, Long>{

}
