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

import com.backend.dao.ITipoContribuyenteDao;
import com.backend.model.TipoContribuyente;
import com.backend.response.TipoContribuyenteResponseRest;

@Service
public class TipoContribuyenteServiceImpl implements ITipoContribuyenteService {

	private static final Logger Log = LoggerFactory.getLogger(TipoContribuyenteServiceImpl.class);

	@Autowired
	private ITipoContribuyenteDao tipoContribuyenteDao;

	@Override
	@Transactional
	public ResponseEntity<TipoContribuyenteResponseRest> listarTipoContribuyente() {

		Log.info("Inicio metodo listar tipo contribuyente()");

		TipoContribuyenteResponseRest response = new TipoContribuyenteResponseRest();

		try {
			List<TipoContribuyente> tipoContribuyente = (List<TipoContribuyente>) tipoContribuyenteDao.findAll();

			response.getTipoContribuyenteResponse().setTipoContribuyente(tipoContribuyente);

			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetada("Respuesta no ok", "-1", "Respuesta incorrecta");
			Log.error("Error al consultar tipo contribuyente: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<TipoContribuyenteResponseRest> consultarPorId(Long id) {
		
		Log.info("Inicio método consultarPorId - tipo contribuyente");
		
		TipoContribuyenteResponseRest response = new TipoContribuyenteResponseRest();
		List<TipoContribuyente> list = new ArrayList<>();
		
		try {
			
			Optional<TipoContribuyente> tipoContribuyente = tipoContribuyenteDao.findById(id);
			
			if (tipoContribuyente.isPresent()) {
				list.add(tipoContribuyente.get());
				response.getTipoContribuyenteResponse().setTipoContribuyente(list);
			} else {
				Log.error("Error en consultar el tipo contribuyente");
				response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no encontrada");
				return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error en consultar el tipo contribuyente");
			response.setMetada("Respuesta no ok", "-1", "Error en consultar el tipo contribuyente");
			return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoContribuyenteResponseRest> crear(TipoContribuyente tipoContribuyente) {
		
		Log.info("Inicio método crear tipo contribuyente");
		
		TipoContribuyenteResponseRest response = new TipoContribuyenteResponseRest();
		List<TipoContribuyente> list = new ArrayList<>();
		
		try {
			
			TipoContribuyente tipoContribuyenteGuardada = tipoContribuyenteDao.save(tipoContribuyente);
			
			if (tipoContribuyenteGuardada != null) {
				list.add(tipoContribuyenteGuardada);
				response.getTipoContribuyenteResponse().setTipoContribuyente(list);
			} else {
				Log.error("Error en grabar el tipo contribuyente");
				response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no guardada");
				return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			Log.error("Error en grabar el tipo contribuyente");
			response.setMetada("Respuesta no ok", "-1", "Error en tipo contribuyente");
			return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Tipo contribuyente creada");
		return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoContribuyenteResponseRest> actualizar(TipoContribuyente tipoContribuyente, Long id) {
		
		Log.info("Inicio método editar tipo contribuyente");
		
		TipoContribuyenteResponseRest response = new TipoContribuyenteResponseRest();
		List<TipoContribuyente> list = new ArrayList<>();
		
		try {
			
			Optional<TipoContribuyente> tipoContribuyenteBuscada = tipoContribuyenteDao.findById(id);
			
			if (tipoContribuyenteBuscada.isPresent()) {
				tipoContribuyenteBuscada.get().setNombre(tipoContribuyente.getNombre());
				tipoContribuyenteBuscada.get().setEstado(tipoContribuyente.getEstado());
				
				TipoContribuyente tipoContribuyenteActualizada = tipoContribuyenteDao.save(tipoContribuyenteBuscada.get());
				
				if (tipoContribuyenteActualizada != null) {
					response.setMetada("Respuesta ok", "00", "Tipo contribuyente actualizada");
					list.add(tipoContribuyenteActualizada);
					response.getTipoContribuyenteResponse().setTipoContribuyente(list);
				} else {
					Log.error("Error en actualizar tipo contribuyente");
					response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no actualizada");
					return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Log.error("Error en actualizar el tipo contribuyente");
				response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no actualizada");
				return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			Log.error("Error en actualizar el tipo contribuyente", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no actualizada");
			return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoContribuyenteResponseRest> eliminar(Long id) {
		
		Log.info("Inicio método eliminar tipo contribuyente");
		
		TipoContribuyenteResponseRest response = new TipoContribuyenteResponseRest();
		
		try {
			tipoContribuyenteDao.deleteById(id);
			response.setMetada("Respuesta ok", "00", "Tipo contribuyente eliminada");
		} catch (Exception e) {
			Log.error("Error en eliminar el tipo contribuyente", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Tipo contribuyente no eliminada");
			return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<TipoContribuyenteResponseRest>(response, HttpStatus.OK);
	}

	

}
