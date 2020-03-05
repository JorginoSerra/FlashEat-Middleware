package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Localidad;
import es.flasheat.util.DataException;


public interface LocalidadDAO {

	public Localidad findById(Connection connection, Long id) throws DataException;
	
	public List<Localidad> findByProvincia(Connection connection, Long idProvicina) throws DataException;
	

}
