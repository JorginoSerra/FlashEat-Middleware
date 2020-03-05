package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Producto;
import es.flasheat.util.DataException;


public interface ProductoDAO {

public Producto findById(Connection connection, Long id, String idioma) throws DataException;

public List<Producto> findByRestaurante(Connection connection, Long idRestaurante, String idioma) throws DataException;

//No se implementarán en esta aplicacion
//public void insertar(Producto p);
//public void update(Producto p);
//public void delete(int id);
}
