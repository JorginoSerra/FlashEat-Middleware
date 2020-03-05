package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.RestauranteDAO;
import es.flasheat.dao.impl.RestauranteDAOImpl;
import es.flasheat.model.Restaurante;
import es.flasheat.model.criteria.RestauranteCriteria;
import es.flasheat.service.RestauranteService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class RestauranteServiceImpl implements RestauranteService{

	private RestauranteDAO dao = null;

	public RestauranteServiceImpl() {
		dao = new RestauranteDAOImpl();
	}

	public Restaurante findById(Long id, String idioma) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findById(connection, id, idioma);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	public List<Restaurante> findByCriteria(RestauranteCriteria c, String idioma) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByCriteria(connection, c, idioma);

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}
}
