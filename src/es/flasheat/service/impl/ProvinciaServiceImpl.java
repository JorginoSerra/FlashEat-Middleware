package es.flasheat.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.flasheat.dao.ProvinciaDAO;
import es.flasheat.dao.impl.ProvinciaDAOImpl;
import es.flasheat.model.Provincia;
import es.flasheat.service.ProvinciaService;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService{
	
	private ProvinciaDAO dao = null;

	public ProvinciaServiceImpl() {
		dao = new ProvinciaDAOImpl();
	}

	public List<Provincia> findByPais(String idPais) throws DataException{
		Connection connection = null;

		try {

			connection = DBUtils.conectar();
			connection.setAutoCommit(true);

			return dao.findByPais(connection, idPais);	

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}
	
	public List<Provincia> findByNombre(String nombre) throws DataException{
		List<Provincia> results = new ArrayList<Provincia>();
		Provincia e = new Provincia();
		e.setCodPais("ESP");
		e.setCodProvincia((long)27);
		e.setProvincia("Lugo");
		results.add(e);
		Provincia d = new Provincia();
		d.setCodPais("ESP");
		d.setCodProvincia((long)28);
		d.setProvincia("Ourense");
		results.add(d);
		return results;
	}

}
