package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Ubicacion;
import es.flasheat.util.DataException;

	public interface UbicacionDAO {

		public Ubicacion findById(Connection connection, Long id) throws DataException;

		public List<Ubicacion> findByIds(Connection connection, List<Long> ids) throws DataException;	

		public void insertar(Connection connection, Ubicacion u) throws DataException;

		public void update(Connection connection, Ubicacion u) throws DataException;

		public void delete(Connection connection, Long id) throws DataException;
		
}
