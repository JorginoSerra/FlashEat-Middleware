package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import es.flasheat.dao.PedidoDAO;
import es.flasheat.dao.impl.PedidoDAOImpl;
import es.flasheat.model.Pedido;
import es.flasheat.service.PedidoService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class PedidoServiceImpl implements PedidoService{
	private PedidoDAO dao = null;

	public PedidoServiceImpl() {
		dao = new PedidoDAOImpl();
	}

	@Override
	public Pedido findById(Long id) throws DataException {
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

	@Override
	public List<Pedido> findByCliente(Long idCliente) throws DataException {
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByCliente(connection, idCliente);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}
	
	public List<Pedido> findByRestaurante(Long idRestaurante) throws DataException {
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByCliente(connection, idRestaurante);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}

	@Override
	public List<Pedido> findByFecha(Date fechaDesde, Date fechaHasta) throws DataException {
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByFecha(connection, fechaDesde, fechaHasta);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}

	@Override
	public void insertar(Pedido p) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.insertar(connection, p);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}
	
	@Override
	public void insertarCarrito(Pedido p) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.insertarCarrito(connection, p);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}

	@Override
	public Pedido updateStatus(int status, Long id) throws DataException {
		Connection connection = null;
		Pedido p = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			p = dao.updateStatus(connection, status, id);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
		return p;
	}

	@Override
	public void delete(Long id) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.delete(connection, id);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}
		
	}
