package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.LineaPedidoDAO;
import es.flasheat.dao.PedidoDAO;
import es.flasheat.model.LineaPedido;
import es.flasheat.model.Pedido;
import es.flasheat.model.PedidoEstado;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class PedidoDAOImpl implements PedidoDAO {
	
	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());

	private LineaPedidoDAO lineaPedidoDAO = null;

	public PedidoDAOImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	public Pedido findById(Connection connection, Long id) throws DataException{
		Pedido pedido = null;

		Statement statement = null;
		String query = null;
		ResultSet rs = null;

		try {
			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			statement = connection.createStatement();
			query =  " SELECT ID_PEDIDO,ID_USUARIO,ID_RESTAURANTE,FECHA,PRECIO,COMENTARIO,VALORACION,ID_ESTADO,INFORMACION_EXTRA "+
					"FROM Pedido "+
					"WHERE ID_PEDIDO = "+id; // ?

			logger.debug(query);
			rs = statement.executeQuery(query);
			// si hay datos en rs añade un Restaurante a la lista con esos datos.


			if(rs.next()) {
				pedido = (loadNext(connection, rs));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(statement);
		}
		return pedido;
	}

	@Override
	public List<Pedido> findByCliente(Connection connection, Long idCliente) throws DataException{
		List<Pedido> pedidos = new ArrayList<Pedido>();	

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT ID_PEDIDO,ID_USUARIO,ID_RESTAURANTE,FECHA,PRECIO,COMENTARIO,VALORACION,ID_ESTADO,INFORMACION_EXTRA "+
							"FROM Pedido "+
					"WHERE ID_USUARIO = ? AND id_Estado <> 4 ");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);

			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, idCliente);

			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				pedidos.add(loadNext(connection, rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return pedidos;
	}
	
	public List<Pedido> findByRestaurante(Connection connection, Long idRestaurante) throws DataException{
		List<Pedido> pedidos = new ArrayList<Pedido>();	

		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT ID_PEDIDO,ID_USUARIO,ID_RESTAURANTE,FECHA,PRECIO,COMENTARIO,VALORACION,ID_ESTADO,INFORMACION_EXTRA "+
							"FROM Pedido "+
					"WHERE ID_RESTAURANTE = ? AND id_Estado <> 4 ");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);

			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, idRestaurante);

			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				pedidos.add(loadNext(connection, rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return pedidos;
	}

	@Override
	public List<Pedido> findByFecha(Connection connection, Date fechaDesde, Date fechaHasta) throws DataException{
		List<Pedido> pedidos = new ArrayList<Pedido>();	
		
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					" SELECT ID_PEDIDO,ID_USUARIO,ID_RESTAURANTE,FECHA,PRECIO,COMENTARIO,VALORACION,ID_ESTADO,INFORMACION_EXTRA "+
							" FROM Pedido "+
							" WHERE id_Estado <> 4 AND FECHA " +
					" BETWEEN ? AND ?");

			// Componer la query (en sb) segun criterios de búsqueda
			query = sb.toString();
			logger.debug(query);

			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			preparedStatement.setDate(i++, new java.sql.Date(fechaDesde.getTime()));
			preparedStatement.setDate(i++, new java.sql.Date(fechaHasta.getTime()));
			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				pedidos.add(loadNext(connection, rs));
			}
		} catch (SQLException e) {
			logger.error("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return pedidos;
	}



	@Override
	public void insertar(Connection connection, Pedido p) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			// Creamos el preparedstatement
			String queryString = " insert into pedido (precio, fecha, id_usuario, id_restaurante, comentario, id_estado, informacion_extra) "
					+" values (?, ?, ?, ?, ?,"+PedidoEstado.CREADO+",?)";	

			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);


			logger.debug(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setDouble(i++, p.getPrecio());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			preparedStatement.setLong(i++, p.getIdUsuario());
			preparedStatement.setLong(i++, p.getIdRestaurante());
			preparedStatement.setString(i++, p.getComentario());
			preparedStatement.setString(i++, p.getInformacionExtra());

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1); 
				p.setId(id);
			} else {
				// TODO 
				// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXCEPTIONS
				// throw new DataException("Unable to fetch autogenerated primary key");
			}

			for (LineaPedido lp: p.getLineas()) {
				lp.setIdPedido(p.getId());
				lineaPedidoDAO.insertar(connection, lp);
			}

		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		// Return the DTO
		// return p;
	}

	@Override
	public void insertarCarrito(Connection connection, Pedido p) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			// Creamos el preparedstatement
			String queryString = " insert into pedido (precio, fecha, id_restaurante, id_estado) "
					+" values (?, ?, ?, "+PedidoEstado.CREADO+")";	

			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);


			logger.debug(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setDouble(i++, p.getPrecio());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			preparedStatement.setLong(i++, p.getIdRestaurante());

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1); 
				p.setId(id);
			} else {
				// TODO 
				// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXCEPTIONS
				// throw new DataException("Unable to fetch autogenerated primary key");
			}

			for (LineaPedido lp: p.getLineas()) {
				lp.setIdPedido(p.getId());
				lineaPedidoDAO.insertar(connection, lp);
			}

		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		// Return the DTO
		// return p;
	}
	
	@Override
	public Pedido updateStatus(Connection connection, int status, Long id) throws DataException{
		Pedido p = new Pedido();  
		PreparedStatement preparedStatement = null;

		try {

			// Creamos el preparedstatement
			String queryString = " UPDATE PEDIDO" +
					" SET id_estado = ?" + 
					" WHERE id_pedido = ? ";

			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);


			logger.debug(queryString);
			// Fill the "preparedStatement"    
			preparedStatement.setInt(1, status);
			preparedStatement.setLong(2, id);

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			}

		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		// Return the DTO
		return p;
	}
	public void delete(Connection connection, Long idPedido) throws DataException{
		PreparedStatement preparedStatement = null;

		try {

			lineaPedidoDAO.deleteByPedido(connection, idPedido);
			logger.info("LINEAS BORRADAS");
			
			// Creamos el preparedstatement
			String queryString = " DELETE FROM Pedido WHERE id_pedido = ?";	

			preparedStatement = connection.prepareStatement(queryString,
					Statement.RETURN_GENERATED_KEYS);


			logger.debug(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setLong(i++, idPedido);

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not delete to table 'Pedido'");
			}

		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
			
		}
	}
	private Pedido loadNext(Connection connection, ResultSet rs) 
			throws SQLException, DataException {

		int i = 1;
		Pedido pedido = new Pedido();  
		lineaPedidoDAO = new LineaPedidoDAOImpl();
		pedido.setId(rs.getLong(i++));
		pedido.setIdUsuario(rs.getLong(i++));
		pedido.setIdRestaurante(rs.getLong(i++));
		pedido.setFecha(rs.getDate(i++));
		pedido.setPrecio(rs.getDouble(i++));
		pedido.setComentario(rs.getString(i++));
		pedido.setValoracion(rs.getInt(i++));
		pedido.setIdEstado(rs.getInt(i++));
		pedido.setInformacionExtra(rs.getString(i++));
		
		List<LineaPedido> lineas = lineaPedidoDAO.findByPedido(connection, pedido.getId());
		pedido.setLineas(lineas);

		return pedido;
	}

}
