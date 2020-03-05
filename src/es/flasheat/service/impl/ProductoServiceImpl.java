package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.flasheat.dao.ProductoDAO;
import es.flasheat.dao.impl.ProductoDAOImpl;
import es.flasheat.model.Producto;
import es.flasheat.service.ProductoService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class ProductoServiceImpl implements ProductoService{
	
	private ProductoDAO dao = null;

	public ProductoServiceImpl() {
		dao = new ProductoDAOImpl();
	}

public Producto findById(Long id, String idioma) throws DataException{
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

public List<Producto> findByRestaurante(Long idRestaurante, String idioma) throws DataException{
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


}
