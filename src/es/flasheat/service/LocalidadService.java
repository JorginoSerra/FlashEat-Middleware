package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Localidad;
import es.flasheat.util.DataException;

public interface LocalidadService {
	
	public Localidad findById(Long id) throws DataException;
	
	public List<Localidad> findByProvincia(Long idProvicina) throws DataException;

}
