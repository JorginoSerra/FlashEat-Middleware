package es.flasheat.dao;

import java.sql.Connection;

import es.flasheat.model.Ubicacion;
import es.flasheat.model.Usuario;
import es.flasheat.util.DataException;

public interface UsuarioDAO {

public Usuario findById(Connection connection, Long id) throws DataException;

public Usuario findByEmail(Connection connection, String Email) throws DataException;

public void insertar(Connection connection, Usuario u) throws DataException;

public void update(Connection connection, Usuario u) throws DataException;

public void delete(Connection connection, Long id) throws DataException;

public void addUbicacion(Connection connection, Long idUsuario, Ubicacion u) throws DataException;

public void deleteUbicacion(Connection connection, Long idUsuario, Long idUbicacion) throws DataException;

public void addSeguirRestaurante(Connection connection, Long idUsuario, Long idRestaurante) throws DataException;

public void deleteSeguirRestaurante(Connection connection, Long idUsuario, Long idRestaurante) throws DataException;


}
