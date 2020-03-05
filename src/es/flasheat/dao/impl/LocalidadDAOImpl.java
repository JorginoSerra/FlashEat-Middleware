package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.LocalidadDAO;
import es.flasheat.model.Localidad;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class LocalidadDAOImpl implements LocalidadDAO{
	
	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());
	
	public Localidad findById(Connection connection, Long id) throws DataException{
		Localidad localidad = null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		
		try {
			query = "SELECT ID_Localidad,NOMBRE, id_provincia "+
					"FROM Localidad "+
					"WHERE ID_Localidad = ?";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);
			int i = 1;
			preparedStatement.setLong(i++, id);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			if(rs.next()) {
				localidad=loadNext(rs);
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return localidad;
	}
	
	public List<Localidad> findByProvincia(Connection connection, Long idProvicina) throws DataException{
		List<Localidad> localidades = new ArrayList<Localidad>();	
		Localidad localidad = null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		
		try {
			query = "SELECT ID_Localidad,NOMBRE, id_provincia "+
					"FROM Localidad "+
					"WHERE id_provincia = ?";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);
			int i = 1;
			preparedStatement.setLong(i++, idProvicina);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			while(rs.next()) {
				localidades.add(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return localidades;
	}
	
	protected Localidad loadNext(ResultSet rs)
			throws SQLException {	
		int i = 1;
		Localidad l = new Localidad();
		l.setId(rs.getLong(i++));
		l.setNombre(rs.getString(i++));
		l.setIdProvincia(rs.getLong(i++));
		return l;
	}
}
