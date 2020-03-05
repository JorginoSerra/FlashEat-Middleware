package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import es.flasheat.dao.UsuarioDAO;
import es.flasheat.dao.impl.UsuarioDAOImpl;
import es.flasheat.model.Ubicacion;
import es.flasheat.model.Usuario;
import es.flasheat.service.MailService;
import es.flasheat.service.UsuarioService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;
import es.flasheat.util.MailException;

public class UsuarioServiceImpl implements UsuarioService{


	private UsuarioDAO dao = null;

	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	}

	@Override
	public Usuario findById(Long id) throws DataException {
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
	public Usuario findByEmail(String email) throws DataException {
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByEmail(connection, email);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public void insertar(Usuario u) throws DataException, MailException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			dao.insertar(connection, u);
			MailService mail = new MailServiceImpl();
			mail.sendMail("BIENVENIDO A FLASHEAT", "Hola , gracias por registrarte en Flash Eat", u.getEmail());
			x = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}

	@Override
	public void update(Usuario u) throws DataException {
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

	@Override
	public void addUbicacion(Long idUsuario, Ubicacion u) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			dao.addUbicacion(connection, idUsuario, u);
			x = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}

	@Override
	public void deleteUbicacion(Long idUsuario, Long idUbicacion) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			dao.deleteUbicacion(connection, idUsuario, idUbicacion);
			x = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}

	@Override
	public void addSeguirRestaurante(Long idUsuario, Long idRestaurante) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			dao.addSeguirRestaurante(connection, idUsuario, idRestaurante);
			x = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}

	@Override
	public void deleteSeguirRestaurante(Long idUsuario, Long idRestaurante) throws DataException {
		Connection connection = null;
		boolean x = false;
		try {
			connection = DBUtils.conectar(); // ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);		

			connection.setAutoCommit(false);

			dao.deleteSeguirRestaurante(connection, idUsuario, idRestaurante);
			x = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection, x);
		}
	}
}
