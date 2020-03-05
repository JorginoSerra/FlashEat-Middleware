package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.ProductoDAO;
import es.flasheat.model.Producto;
import es.flasheat.util.DBUtils;
import es.flasheat.util.JDBCUtils;
import es.flasheat.util.DataException;
public class ProductoDAOImpl implements ProductoDAO{

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());

	@Override
	public Producto findById(Connection connection, Long id, String idioma) throws DataException{
		Producto producto = new Producto();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {
			query = "SELECT P.ID_PRODUCTO,P.ID_RESTAURANTE,TITULO,DESCRIPCION, P.PRECIO "+
					"FROM PRODUCTO P "+
					"INNER JOIN IDIOMA_PRODUCTO IP ON IP.ID_PRODUCTO = P.ID_PRODUCTO "+
					"WHERE P.ID_PRODUCTO = ? AND IP.ID_IDIOMA = ? ";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);

			int i = 1;
			preparedStatement.setLong(i++, id);
			preparedStatement.setString(i++, idioma);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			if(rs.next()) {
				producto=(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return producto;
	}

	@Override
	public List<Producto> findByRestaurante(Connection connection, Long idRestaurante, String idioma) throws DataException{
		List<Producto> productos = new ArrayList<Producto>();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {
			query = "SELECT P.ID_PRODUCTO,P.ID_RESTAURANTE,TITULO,DESCRIPCION,P.PRECIO "+
					"FROM PRODUCTO P "+
					"INNER JOIN IDIOMA_PRODUCTO IP ON IP.ID_PRODUCTO = P.ID_PRODUCTO "+
					"WHERE P.ID_RESTAURANTE = ? AND IP.ID_IDIOMA = ? ";

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			preparedStatement = connection.prepareStatement(query);


			logger.debug(query);

			int i = 1;
			preparedStatement.setLong(i++, idRestaurante);
			preparedStatement.setString(i++, idioma);


			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			i=1;
			while(rs.next()) {
				productos.add(loadNext(rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return productos;

	}

	protected Producto loadNext(ResultSet rs)
			throws SQLException {	
		int i = 1;
		Producto p = new Producto();
		p.setId(rs.getLong(i++));
		p.setIdRestaurante(rs.getLong(i++));
		p.setNombre(rs.getString(i++));
		p.setDescripcion(rs.getString(i++));	
		p.setPrecio(rs.getDouble(i++));

		return p;
	}
}