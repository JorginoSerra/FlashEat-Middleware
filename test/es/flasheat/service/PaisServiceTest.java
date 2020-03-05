package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Pais;
import es.flasheat.service.impl.PaisServiceImpl;
import es.flasheat.util.DataException;

public class PaisServiceTest {
	
private PaisService service = null;
	
	public PaisServiceTest() {
		service = new PaisServiceImpl();
	}
	
	public void testFindById() throws DataException {
		Pais pais = service.findById("ES");
		System.out.println(pais);
	}
	
	public void testFindByAll() throws DataException {
		List<Pais> p = service.findAll();
		for(Pais pais: p)
		System.out.println(pais);
	}
	
	public static void main(String[] args) throws DataException {
		PaisServiceTest test = new PaisServiceTest() ;
		test.testFindById();
		test.testFindByAll();
		

	}
}
