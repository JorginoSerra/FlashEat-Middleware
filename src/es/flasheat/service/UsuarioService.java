package es.flasheat.service;

import es.flasheat.model.Ubicacion;
import es.flasheat.model.Usuario;
import es.flasheat.util.DataException;
import es.flasheat.util.MailException;

public interface UsuarioService {

	public Usuario findById(Long id) throws DataException;

	public Usuario findByEmail(String Email) throws DataException;

	public void insertar(Usuario u) throws DataException,MailException ;

	public void update(Usuario u) throws DataException;

	public void delete(Long id) throws DataException;

	public void addUbicacion(Long idUsuario, Ubicacion u) throws DataException;

	public void deleteUbicacion(Long idUsuario, Long idUbicacion) throws DataException;

	public void addSeguirRestaurante(Long idUsuario, Long idRestaurante) throws DataException;

	public void deleteSeguirRestaurante(Long idUsuario, Long idRestaurante) throws DataException;
	
}
