package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.LineaPedidoDAO;
import es.flasheat.dao.impl.LineaPedidoDAOImpl;
import es.flasheat.model.LineaPedido;
import es.flasheat.service.LineaPedidoService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class LineaPedidoServiceImpl implements LineaPedidoService{

	private LineaPedidoDAO dao = null;

	public LineaPedidoServiceImpl() {
		dao = new LineaPedidoDAOImpl();
	}


	public List<LineaPedido> findByPedido(Long idPedido) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByPedido(connection, idPedido);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}

	public void insertar(LineaPedido lp) throws DataException{
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.insertar(connection, lp);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}

	}
	public void deleteByPedido(Long idPedido) throws DataException{
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.deleteByPedido(connection, idPedido);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}

	}

}
