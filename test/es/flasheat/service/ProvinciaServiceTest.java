package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Provincia;
import es.flasheat.service.impl.ProvinciaServiceImpl;
import es.flasheat.util.DataException;

public class ProvinciaServiceTest {

	private ProvinciaService service = null;
	
	public ProvinciaServiceTest() {
		service = new ProvinciaServiceImpl() ;
	}
	
	public void testFindByAll() throws DataException {
		List<Provincia> c = service.findByPais("ES");
		for(Provincia provincia: c)
		System.out.println(provincia);
	}
	
	public static void main(String[] args) throws DataException {
		ProvinciaServiceTest test = new ProvinciaServiceTest() ;
		test.testFindByAll();
	}
}
