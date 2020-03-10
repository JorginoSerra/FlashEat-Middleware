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

import es.flasheat.dao.RestauranteDAO;
import es.flasheat.model.Restaurante;
import es.flasheat.model.criteria.RestauranteCriteria;
import es.flasheat.util.DBUtils;
import es.flasheat.util.DataException;
import es.flasheat.util.JDBCUtils;
import es.flasheat.util.SQLUtils;

public class RestauranteDAOImpl implements RestauranteDAO  {
	
	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());
	
	PreparedStatement preparedStatement = null;
	String query = null;
	ResultSet rs = null;
	public Restaurante findById(Connection connection, Long id, String idioma) throws DataException{	
		
		Restaurante r = null;
		Statement statement = null;
		try {
			//Crear una sentencia SQL y Meter en rs el resultado de la query.
			statement = connection.createStatement();
			query = " SELECT R.ID_RESTAURANTE, R.Nombre, R.EMAIL, R.DESCUENTO,R.PRECIO_ENVIO, R.ID_UBICACION, R.ID_CATEGORIA,R.TELEFONO, RI.DESCRIPCION, AVG(P.Valoracion) VALORACION "+
					" FROM RESTAURANTE R "+
					" LEFT JOIN PEDIDO P ON R.ID_RESTAURANTE = P.ID_RESTAURANTE "+
					"INNER JOIN RESTAURANTE_IDIOMA RI ON R.ID_RESTAURANTE = RI.ID_RESTAURANTE "+
					"WHERE RI.ID_IDIOMA LIKE '"+idioma+"' AND R.ID_RESTAURANTE = "+id+
					" GROUP BY R.ID_RESTAURANTE";
			
			System.out.println(query);
			rs = statement.executeQuery(query);
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			int i=1;
			if(rs.next()) {
				r = loadNext(rs);
			}
		} catch (SQLException e) {
			System.out.println("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return r;
	}
	
	

	public List<Restaurante> findByCriteria(Connection connection, RestauranteCriteria criterio, String idioma) throws DataException{	
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();	

		try {

			//Crear una sentencia SQL y meter en rs el resultado de la query.
			StringBuilder querySB = new StringBuilder( 
					" SELECT R.ID_RESTAURANTE, R.Nombre, R.EMAIL, R.DESCUENTO,R.PRECIO_ENVIO, R.ID_UBICACION, R.ID_CATEGORIA,R.TELEFONO, RI.DESCRIPCION, AVG(P.Valoracion) VALORACION "+
					" FROM RESTAURANTE r "+
					" LEFT JOIN PEDIDO P ON R.ID_RESTAURANTE = P.ID_RESTAURANTE "+
					" INNER JOIN RESTAURANTE_IDIOMA ri ON r.ID_RESTAURANTE = ri.ID_RESTAURANTE "
					);
			
			if (criterio.getLocalidad()!=null) {
					querySB.append("       INNER JOIN UBICACION u ON R.ID_UBICACION = u.ID_UBICACION "
							+"       INNER JOIN LOCALIDAD l ON U.ID_LOCALIDAD = l.ID_LOCALIDAD "); 
			}
			if (criterio.getIdProvincia()!=null) {
				if (criterio.getLocalidad()==null) {
					querySB.append("       INNER JOIN UBICACION u ON R.ID_UBICACION = u.ID_UBICACION "
							+"       INNER JOIN LOCALIDAD l ON U.ID_LOCALIDAD = l.ID_LOCALIDAD "); 
				}
				querySB.append("       INNER JOIN PROVINCIA pr ON l.ID_PROVINCIA = pr.ID_PROVINCIA "); 
		}
			boolean first = false;
			querySB.append("WHERE ri.ID_IDIOMA = ? ");
			first = SQLUtils.addClause(criterio.getCategoria(), querySB, first, " r.ID_CATEGORIA = ? ");
			
			first = SQLUtils.addClause(criterio.isEnvioGratis()!=null && criterio.isEnvioGratis(), 
												querySB, first, " r.PRECIO_ENVIO = 0 ");
			
			first = SQLUtils.addClause(criterio.getLocalidad(), querySB, first," L.ID_LOCALIDAD = ? ");
			
			first = SQLUtils.addClause(criterio.getIdProvincia(), querySB, first, " pr.ID_PROVINCIA = ? ");
			
			querySB.append(" GROUP BY R.ID_RESTAURANTE ");
			//Tiene que ir al final
			if (criterio.getValoracion()!=null) {
				querySB.append(" HAVING AVG(P.VALORACION) >= ? ");
			}
					
			// Componer la query (en sb) segun criterios de búsqueda
			query = querySB.toString();
			System.out.println(query);
			
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setString(i++, idioma);
			if  (criterio.getCategoria()!=null) {
				preparedStatement.setLong(i++, criterio.getCategoria());
			}
			if  (criterio.getLocalidad()!=null) {
				preparedStatement.setLong(i++, criterio.getLocalidad());
			}
			if  (criterio.getValoracion()!=null) {
				preparedStatement.setInt(i++, criterio.getValoracion());
			}
			if  (criterio.getIdProvincia()!=null) {
				preparedStatement.setLong(i++, criterio.getIdProvincia());
			}			
			rs = preparedStatement.executeQuery();
			// si hay datos en rs añade un Restaurante a la lista con esos datos.

			while (rs.next()) {
				restaurantes.add(loadNext(rs));
			}
			
		} catch (SQLException e) {
			System.out.println("Problema en la Query.");
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return restaurantes;
	}

	
	protected Restaurante loadNext(ResultSet rs)
					throws SQLException {	
		int i = 1;
		Long idRestaurante = rs.getLong(i++);
		String nombre = rs.getString(i++);
		String email = rs.getString(i++);
		Double descuento = rs.getDouble(i++);
		Double precioEnvio = rs.getDouble(i++);
		Long idUbicacion = rs.getLong(i++);
		Long idCategoria = rs.getLong(i++);
		String telefono = rs.getString(i++);
		String descripcion = rs.getString(i++);
		Double valoracion= rs.getDouble(i++);
		Restaurante r = new Restaurante();
		
		r.setId(idRestaurante);
		r.setNombre(nombre);
		r.setEmail(email);
		r.setDescuento(descuento);
		r.setIdCategoria(idCategoria);
		r.setIdUbicacion(idUbicacion);
		r.setPrecioEnvio(precioEnvio);
		r.setTelefono(telefono);
		r.setDescripcion(descripcion);
		r.setValoracion(valoracion);

		return r;
	}

}