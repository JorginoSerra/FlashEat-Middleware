package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Pais;
import es.flasheat.util.DataException;

public interface PaisDAO {
	
	public Pais findById(Connection connection, String id) throws DataException;

	public List<Pais> findAll(Connection connection) throws DataException;

}