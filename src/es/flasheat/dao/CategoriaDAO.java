package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Categoria;
import es.flasheat.util.DataException;

public interface CategoriaDAO {

public Categoria findById(Connection connection, Long id, String idioma) throws DataException;

public Categoria findByRestaurante(Connection connection, Long idRestaurante, String idioma) throws DataException;

public List<Categoria> findAll(Connection connection, String idioma) throws DataException;


}
