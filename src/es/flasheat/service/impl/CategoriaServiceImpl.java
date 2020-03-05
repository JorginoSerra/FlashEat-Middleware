package es.flasheat.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.CategoriaDAO;
import es.flasheat.dao.impl.CategoriaDAOImpl;
import es.flasheat.model.Categoria;
import es.flasheat.service.CategoriaService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;


public class CategoriaServiceImpl implements CategoriaService{
	
	private CategoriaDAO dao = null;

	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl();
	}

	@Override
	public Categoria findById(Long id, String idioma) throws DataException{

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
	
	public Categoria findByRestaurante(Long idRestaurante, String idioma) throws DataException{

		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByRestaurante(connection, idRestaurante, idioma);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}
	
	public List<Categoria> findAll(String idioma) throws DataException{

		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findAll(connection, idioma);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
			}
		}


}
