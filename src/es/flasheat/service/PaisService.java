package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Pais;
import es.flasheat.util.DataException;

public interface PaisService {

	public Pais findById(String id) throws DataException;

	public List<Pais> findAll() throws DataException;
	
}
