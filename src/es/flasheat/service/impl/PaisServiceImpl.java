package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.PaisDAO;
import es.flasheat.dao.impl.PaisDAOImpl;
import es.flasheat.model.Pais;
import es.flasheat.service.PaisService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class PaisServiceImpl implements PaisService {
	
	private PaisDAO dao = null;

	public PaisServiceImpl() {
		dao = new PaisDAOImpl();
	}

	public Pais findById(String id) throws DataException{
		
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

	public List<Pais> findAll() throws DataException{
		
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findAll(connection);

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}
		
	}
	
