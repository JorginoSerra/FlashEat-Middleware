package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Provincia;
import es.flasheat.util.DataException;

public interface ProvinciaService {

	public List<Provincia> findByPais(String idPais) throws DataException;
	
	public List<Provincia> findByNombre(String nombre) throws DataException;
}
