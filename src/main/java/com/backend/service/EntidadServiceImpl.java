package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.IEntidadDao;
import com.backend.model.Entidad;
import com.backend.response.EntidadResponseRest;

@Service
public class EntidadServiceImpl implements IEntidadService {
	
	private static final Logger Log = LoggerFactory.getLogger(EntidadServiceImpl.class);
	
	@Autowired
	private IEntidadDao entidadDao;
	
	@Override
	@Transactional
	public ResponseEntity<EntidadResponseRest> buscarEntidad() {
		
		Log.info("Inicio metodo buscar()");
		
		EntidadResponseRest response = new EntidadResponseRest();
		
		try {
			List<Entidad> entidad = (List<Entidad>) entidadDao.findAll();
			
			response.getEntidadResponse().setEntidad(entidad);
			
			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetada("Respuesta no ok", "-1", "Respuesta incorrecta");
			Log.error("Error al consultar entidad: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EntidadResponseRest> consultarPorId(Long id) {
		
		Log.info("Inicio método buscarPorId");
		
		EntidadResponseRest response = new EntidadResponseRest();
		List<Entidad> list = new ArrayList<>();
		
		try {
			
			Optional<Entidad> entidad = entidadDao.findById(id);
			
			if (entidad.isPresent()) {
				list.add(entidad.get());
				response.getEntidadResponse().setEntidad(list);
			} else {
				Log.error("Error en consultar la entidad");
				response.setMetada("Respuesta no ok", "-1", "Entidad no encontrada");
				return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error en consultar la entidad");
			response.setMetada("Respuesta no ok", "-1", "Error en consultar entidad");
			return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<EntidadResponseRest> crear(Entidad entidad) {
		
		Log.info("Inicio método crear entidad");
		
		EntidadResponseRest response = new EntidadResponseRest();
		List<Entidad> list = new ArrayList<>();
		
		try {
			
			Entidad entidadGuardada = entidadDao.save(entidad);
			
			if (entidadGuardada != null) {
				list.add(entidadGuardada);
				response.getEntidadResponse().setEntidad(list);
			} else {
				Log.error("Error en grabar la entidad");
				response.setMetada("Respuesta no ok", "-1", "Entidad no guardada");
				return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			Log.error("Error en grabar la entidad");
			response.setMetada("Respuesta no ok", "-1", "Error en grabar entidad");
			return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Entidad creada");
		return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EntidadResponseRest> actualizar(Entidad entidad, Long id) {
		
		Log.info("Inicio método editar entidad");
		
		EntidadResponseRest response = new EntidadResponseRest();
		List<Entidad> list = new ArrayList<>();
		
		try {
			
			Optional<Entidad> entidadBuscada = entidadDao.findById(id);
			
			if (entidadBuscada.isPresent()) {
				entidadBuscada.get().setNroDocumento(entidad.getNroDocumento());
				entidadBuscada.get().setRazonSocial(entidad.getRazonSocial());
				entidadBuscada.get().setNombreComercial(entidad.getNombreComercial());
				entidadBuscada.get().setDireccion(entidad.getDireccion());
				entidadBuscada.get().setTelefono(entidad.getTelefono());
				entidadBuscada.get().setEstado(entidad.getEstado());
				
				Entidad entidadActualizada = entidadDao.save(entidadBuscada.get());
				
				if (entidadActualizada != null) {
					response.setMetada("Respuesta ok", "00", "Entidad actualizada");
					list.add(entidadActualizada);
					response.getEntidadResponse().setEntidad(list);
				} else {
					Log.error("Error en actualizar la entidad");
					response.setMetada("Respuesta no ok", "-1", "Entidad no actualizada");
					return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Log.error("Error en actualizar la entidad");
				response.setMetada("Respuesta no ok", "-1", "Entidad no actualizada");
				return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			Log.error("Error en actualizar la entidad", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Entidad no actualizada");
			return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EntidadResponseRest> eliminar(Long id) {
		
		Log.info("Inicio método eliminar entidad");
		
		EntidadResponseRest response = new EntidadResponseRest();
		
		try {
			entidadDao.deleteById(id);
			response.setMetada("Respuesta ok", "00", "Entidad eliminada");
		} catch (Exception e) {
			Log.error("Error en eliminar la entidad", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Entidad no eliminada");
			return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EntidadResponseRest>(response, HttpStatus.OK);
		
	}

	

}
