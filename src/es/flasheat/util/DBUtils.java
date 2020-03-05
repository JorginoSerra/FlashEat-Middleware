package es.flasheat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.flasheat.dao.impl.CategoriaDAOImpl;

public class DBUtils {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/flasheat";
	private static final String USER ="root";
	private static final String PASS ="chantada";

	public DBUtils() {
		
	}
	public static Connection conectar() {
		
		Logger logger = LogManager.getLogger(CategoriaDAOImpl.class.getName());

		Connection conexion = null;

		try{
			Class.forName(CONTROLADOR);

			conexion = DriverManager.getConnection(URL, USER, PASS);

			logger.debug("Conexion OK!");
			
		} catch (ClassNotFoundException e){
			logger.error("Error en el controlador.", e);
		} catch (SQLException e){
			logger.error("Error en la conexion.", e);
		}
		System.out.println(" ");
		return conexion;

	}

}
