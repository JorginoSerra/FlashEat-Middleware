package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Provincia;
import es.flasheat.util.DataException;

public interface ProvinciaDAO {
	
	public List<Provincia> findByPais(Connection connection, String idPais) throws DataException;

}
