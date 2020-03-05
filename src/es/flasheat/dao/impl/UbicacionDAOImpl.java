package es.flasheat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.UbicacionDAO;
import es.flasheat.model.Ubicacion;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;
import es.flasheat.util.SQLUtils;

public class UbicacionDAOImpl implements UbicacionDAO {

	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());

	public Ubicacion findById(Connection connection, Long id) throws DataException{
		Ubicacion ubicacion = null;
		Connection cn = null;
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		cn = DBUtils.conectar();

		try {

			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			StringBuilder sb = new StringBuilder(
					"select u.ID_UBICACION,u.NOMBRE,u.ID_LOCALIDAD "+
							"from UBICACION u "+
					"where u.ID_UBICACION = ?");

			// Componer la query (en sb) segun criterios de b�squeda
			query = sb.toString();
			System.out.println(query);

			preparedStatement = cn.prepareStatement(query);

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery();
			// si hay datos en rs a�ade un Restaurante a la lista con esos datos.

			if (rs.next()) {
				ubicacion = loadNext(rs);
			}
		} catch (SQLException e) {
			System.out.println("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return ubicacion;
	}


	@Override
	public List<Ubicacion> findByIds(Connection connection, List<Long> ids) throws DataException{
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();	
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder(

					" select u.ID_UBICACION,u.NOMBRE,u.ID_LOCALIDAD "+
							" from UBICACION u "+
					" where u.ID_UBICACION in (?) ");

			query = sb.toString();
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			preparedStatement.setString(i++, SQLUtils.toSQLIn(ids));
			rs = preparedStatement.executeQuery();
			// si hay datos en rs a�ade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				ubicaciones.add(loadNext(rs));
			}
		}catch (SQLException e) {
			System.out.println("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return ubicaciones;

	}

	@Override
	public void insertar(Connection connection, Ubicacion u) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			// Creamos el preparedstatement
			String queryString = " insert into ubicacion (NOMBRE, ID_LOCALIDAD) "
								+" values (?, ?)";
			
			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			
			System.out.println(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setString(i++, u.getNombre());
			preparedStatement.setDouble(i++, u.getIdLocalidad());

			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Ubicacion'");
			}
						
			// Recuperamos la PK generada
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				Long id = rs.getLong(1); 
				u.setId(id);
			} else {
				// TODO 
				// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXCEPTIONS
				// throw new DataException("Unable to fetch autogenerated primary key");
			}
			
		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
			// Return the DTO
			//return u;
	}

	@Override
	public void update(Connection connection, Ubicacion u) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Creamos el preparedstatement
			String queryString = " UPDATE Usuario" +
					" SET Nombre = ?, Localidad = ?"+
					" WHERE id_ubicacion = ?";
			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			
			System.out.println(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setString(i++, u.getNombre());
			preparedStatement.setLong(i++, u.getIdLocalidad());
			preparedStatement.setLong(i++, u.getId());
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Ubicacion'");
			}
						
		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
	} finally {
		JDBCUtils.closeResultSet(resultSet);
		JDBCUtils.closeStatement(preparedStatement);
	}
	}

	@Override
	public void delete(Connection connection, Long id) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			// Creamos el preparedstatement
			String queryString = " DELETE FROM Ubicacion WHERE id_ubicacion = ?";
			
			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			
			System.out.println(queryString);
			// Fill the "preparedStatement"
			int i = 1;             
			preparedStatement.setLong(i++, id);
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Ubicacion'");
			}
						
		} catch (Exception e) {
			// TODO EXPLICAR EXCEPTIONS
			// PENDIENTE DE QUE EL PROFE EXPLIQUE LAS EXEPTIONS
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private Ubicacion loadNext(ResultSet rs) 
			throws SQLException {

		int i = 1;
		Ubicacion ubicacion = new Ubicacion();  

		ubicacion.setId(rs.getLong(i++));
		ubicacion.setNombre(rs.getString(i++));
		ubicacion.setIdLocalidad(rs.getLong(i++));

		return ubicacion;
	}

}
