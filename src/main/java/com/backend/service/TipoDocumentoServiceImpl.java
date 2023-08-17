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

import com.backend.dao.ITipoDocumentoDao;
import com.backend.model.TipoDocumento;
import com.backend.response.TipoDocumentoResponseRest;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {
	
	private static final Logger Log = LoggerFactory.getLogger(TipoDocumentoServiceImpl.class);
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;

	@Override
	@Transactional
	public ResponseEntity<TipoDocumentoResponseRest> listarTipoDocumento() {
		
		Log.info("Inicio metodo tipo documento()");
		
		TipoDocumentoResponseRest response = new TipoDocumentoResponseRest();
		
		try {
			List<TipoDocumento> tipoDocumento = (List<TipoDocumento>) tipoDocumentoDao.findAll();
			
			response.getTipoDocumentoResponse().setTipoDocumento(tipoDocumento);
			
			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetada("Respuesta no ok", "-1", "Respuesta incorrecta");
			Log.error("Error al consultar tipo documento: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<TipoDocumentoResponseRest> consultarPorId(Long id) {
		
		Log.info("Inicio método buscarPorId - tipo documento");
		
		TipoDocumentoResponseRest response = new TipoDocumentoResponseRest();
		List<TipoDocumento> list = new ArrayList<>();
		
		try {
			
			Optional<TipoDocumento> tipoDocumento = tipoDocumentoDao.findById(id);
			
			if (tipoDocumento.isPresent()) {
				list.add(tipoDocumento.get());
				response.getTipoDocumentoResponse().setTipoDocumento(list);
			} else {
				Log.error("Error en consultar el tipo de cambio");
				response.setMetada("Respuesta no ok", "-1", "Tipo de cambio no encontrada");
				return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error en consultar la tipo de cambio");
			response.setMetada("Respuesta no ok", "-1", "Error en consultar tipo de cambio");
			return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoDocumentoResponseRest> crear(TipoDocumento tipoDocumento) {
		
		Log.info("Inicio método crear tipo documento");
		
		TipoDocumentoResponseRest response = new TipoDocumentoResponseRest();
		List<TipoDocumento> list = new ArrayList<>();
		
		try {
			
			TipoDocumento tipoDocumentoGuardada = tipoDocumentoDao.save(tipoDocumento);
			
			if (tipoDocumentoGuardada != null) {
				list.add(tipoDocumentoGuardada);
				response.getTipoDocumentoResponse().setTipoDocumento(list);
			} else {
				Log.error("Error en grabar el tipo documento");
				response.setMetada("Respuesta no ok", "-1", "Tipo documento no guardada");
				return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			Log.error("Error en grabar el tipo de documento");
			response.setMetada("Respuesta no ok", "-1", "Error en grabar tipo de documento");
			return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "00", "Tipo documento creada");
		return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoDocumentoResponseRest> actualizar(TipoDocumento tipoDocumento, Long id) {
		
		Log.info("Inicio método editar tipo documento");
		
		TipoDocumentoResponseRest response = new TipoDocumentoResponseRest();
		List<TipoDocumento> list = new ArrayList<>();
		
		try {
			
			Optional<TipoDocumento> tipoDocumentoBuscada = tipoDocumentoDao.findById(id);
			
			if (tipoDocumentoBuscada.isPresent()) {
				tipoDocumentoBuscada.get().setCodigo(tipoDocumento.getCodigo());
				tipoDocumentoBuscada.get().setNombre(tipoDocumento.getNombre());
				tipoDocumentoBuscada.get().setDescripcion(tipoDocumento.getDescripcion());
				tipoDocumentoBuscada.get().setEstado(tipoDocumento.getEstado());
				
				TipoDocumento tipoDocumentoActualizada = tipoDocumentoDao.save(tipoDocumentoBuscada.get());
				
				if (tipoDocumentoActualizada != null) {
					response.setMetada("Respuesta ok", "00", "Tipo documento actualizada");
					list.add(tipoDocumentoActualizada);
					response.getTipoDocumentoResponse().setTipoDocumento(list);
				} else {
					Log.error("Error en actualizar el tipo documento");
					response.setMetada("Respuesta no ok", "-1", "Tipo documento no actualizada");
					return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Log.error("Error en actualizar el tipo de documento");
				response.setMetada("Respuesta no ok", "-1", "Tipo de documento no actualizada");
				return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			Log.error("Error en actualizar el tipo documento", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Tipo documento no actualizada");
			return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TipoDocumentoResponseRest> eliminar(Long id) {
		
		Log.info("Inicio método eliminar tipo documento");
		
		TipoDocumentoResponseRest response = new TipoDocumentoResponseRest();
		
		try {
			tipoDocumentoDao.deleteById(id);
			response.setMetada("Respuesta ok", "00", "Tipo documento eliminada");
		} catch (Exception e) {
			Log.error("Error en eliminar el tipo documento", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Tipo documento no eliminada");
			return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<TipoDocumentoResponseRest>(response, HttpStatus.OK);
	}

	

}
