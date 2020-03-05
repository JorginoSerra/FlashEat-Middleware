package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.ProvinciaDAO;
import es.flasheat.model.Provincia;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class ProvinciaDAOImpl implements ProvinciaDAO{

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());


	public List<Provincia> findByPais(Connection connection, String idPais) throws DataException{
		List<Provincia> provincias = new ArrayList<Provincia>();;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		
		try {
			query = "SELECT ID_PROVINCIA,NOMBRE,ID_PAIS "+
					"FROM PROVINCIA "+
					"WHERE ID_PAIS = ? ";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);

			int i = 1;
			preparedStatement.setString(i++, idPais);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			while(rs.next()) {
				provincias.add(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.", e);
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return provincias;
	}
	
	protected Provincia loadNext(ResultSet rs)
			throws SQLException {	
		int i = 1;
		Provincia p = new Provincia();
		p.setCodProvincia(rs.getLong(i++));
		p.setProvincia(rs.getString(i++));
		p.setCodPais(rs.getString(i++));		

		return p;
	}
	
}
