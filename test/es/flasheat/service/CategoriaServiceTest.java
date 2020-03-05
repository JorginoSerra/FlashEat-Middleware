package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Categoria;
import es.flasheat.service.impl.CategoriaServiceImpl;
import es.flasheat.util.DataException;

public class CategoriaServiceTest {

	private CategoriaService service = null;
	
	public CategoriaServiceTest() {
		service = new CategoriaServiceImpl() ;
	}
	
	public void testFindById() throws DataException {
		Categoria categoria = service.findByRestaurante((long) 3,"ES");
		System.out.println(categoria);
	}
	
	public void testFindByAll() throws DataException {
		List<Categoria> c = service.findAll("ES");
		for(Categoria categoria: c)
		System.out.println(categoria);
	}
	
	public static void main(String[] args) throws DataException {
		CategoriaServiceTest test = new CategoriaServiceTest() ;
		test.testFindById();
		

	}

}
