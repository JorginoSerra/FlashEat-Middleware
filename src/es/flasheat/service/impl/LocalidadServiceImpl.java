package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.LocalidadDAO;
import es.flasheat.dao.impl.LocalidadDAOImpl;
import es.flasheat.model.Localidad;
import es.flasheat.service.LocalidadService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService{
	
	private LocalidadDAO dao = null;

	public LocalidadServiceImpl() {
		dao = new LocalidadDAOImpl();
	}
	public Localidad findById(Long id) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findById(connection, id);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}
	
	public List<Localidad> findByProvincia(Long idLocalidad) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByProvincia(connection, idLocalidad);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}
	

	
}
