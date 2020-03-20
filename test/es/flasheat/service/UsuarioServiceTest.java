package es.flasheat.service;

import java.util.Date;

import es.flasheat.model.Usuario;
import es.flasheat.service.impl.UsuarioServiceImpl;
import es.flasheat.util.DataException;
import es.flasheat.util.MailException;

public class UsuarioServiceTest {

	private UsuarioService dao = null;
	
	public UsuarioServiceTest() {
		dao = new UsuarioServiceImpl() ;
	}
	
	public void testFindById() throws DataException {
		Long l1= new Long(4);
		Long l2= new Long(2);
		dao.addSeguirRestaurante(l1, l2);
	}
	
	public void testAddUser() throws DataException, MailException {
		Usuario u = new Usuario();
		u.setNombre("borrar");
		u.setApellidos("a a");
		u.setEmail("pepspalwldka@gmail.com");
		u.setFechaDeNacimiento(new Date(2000,01,10));
		u.setIdSexo("M");
		u.setTelefono("655778889");
		u.setContraseña("prueba");
		dao.insertar(u);
		
		System.out.println("Usuario Añadido");
	}
	
	public static void main(String[] args) throws DataException, MailException {
		UsuarioServiceTest test = new UsuarioServiceTest() ;
		test.testFindById();

	}

}