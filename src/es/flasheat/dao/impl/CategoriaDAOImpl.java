package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.CategoriaDAO;
import es.flasheat.model.Categoria;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;


public class CategoriaDAOImpl implements CategoriaDAO {

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());
	
	@Override
	public Categoria findById(Connection connection, Long id, String idioma) throws DataException{
		Categoria categoria= null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT id_categoria, nombre "+
							"FROM categoria_idioma "+
							"WHERE ID_CATEGORIA = ? AND ID_IDIOMA = ?");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);
			
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setLong(i++, id);
			preparedStatement.setString(i++, idioma);
			
			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			if (rs.next()) {
				categoria = (loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return categoria;
	}
	
	public Categoria findByRestaurante(Connection connection, Long idRestaurante, String idioma) throws DataException{
		Categoria categoria= null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT ci.ID_CATEGORIA, ci.nombre "+
							"FROM categoria_idioma ci "+
							"INNER JOIN categoria c ON ci.ID_CATEGORIA = c.ID_CATEGORIA "+
							"INNER JOIN restaurante r ON c.ID_CATEGORIA = r.ID_CATEGORIA "+
							"WHERE r.ID_Restaurante = ? AND ci.ID_IDIOMA = ?");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);
			
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setLong(i++, idRestaurante);
			preparedStatement.setString(i++, idioma);
			
			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			if (rs.next()) {
				categoria = (loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return categoria;
	}

	@Override
	public List<Categoria> findAll(Connection connection,String idioma) throws DataException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;


		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT id_categoria, nombre "+
							"FROM categoria_idioma "+
							"WHERE ID_IDIOMA = ?");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);
			
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setString(i++, idioma);
			
			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				categorias.add(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return categorias;
	}


	private Categoria loadNext(ResultSet rs) 
				throws SQLException{

		int i = 1;
		Categoria categoria = new Categoria();  

		categoria.setId(rs.getLong(i++));
		categoria.setNombre(rs.getString(i++));
		
		return categoria;
	}
}