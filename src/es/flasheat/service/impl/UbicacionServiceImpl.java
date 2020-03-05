package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.UbicacionDAO;
import es.flasheat.dao.impl.UbicacionDAOImpl;
import es.flasheat.model.Ubicacion;
import es.flasheat.service.UbicacionService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class UbicacionServiceImpl implements UbicacionService{

	private UbicacionDAO dao = null;

	public UbicacionServiceImpl() {
		dao = new UbicacionDAOImpl();
	}
	

	public Ubicacion findById(Long id) throws DataException{
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

	public List<Ubicacion> findByIds(List<Long> ids) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByIds(connection, ids);

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}



	public void insertar(Ubicacion u) throws DataException{
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.insertar(connection, u);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}


	public void update(Ubicacion u) throws DataException{
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();
			
			connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);		
			
			connection.setAutoCommit(false);
			
			dao.update(connection, u);
			x = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}


	public void delete(Long id) throws DataException{
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
