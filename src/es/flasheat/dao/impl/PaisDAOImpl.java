package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.PaisDAO;
import es.flasheat.model.Pais;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class PaisDAOImpl implements PaisDAO{

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());
	
	public Pais findById(Connection connection, String id) throws DataException{
		Pais pais = null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		
		try {
			query = "SELECT ID_PAIS,NOMBRE "+
					"FROM PAIS "+
					"WHERE ID_PAIS = ?";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);
			int i = 1;
			preparedStatement.setString(i++, id);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			if(rs.next()) {
				pais=(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.debug("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return pais;
	}
	
	public List<Pais> findAll(Connection connection) throws DataException{
		List<Pais> paises = new ArrayList<Pais>();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {
			query = "SELECT ID_PAIS,NOMBRE "+
					"FROM PAIS ";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			
			while(rs.next()) {
				paises.add(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.debug("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return paises;
	}
	
	protected Pais loadNext(ResultSet rs)
			throws SQLException {	
		int i = 1;
		Pais p = new Pais();
		p.setId(rs.getString(i++));
		p.setNombre(rs.getString(i++));
		return p;
	}
	

}
