package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Localidad;
import es.flasheat.service.impl.LocalidadServiceImpl;
import es.flasheat.util.DataException;

public class LocalidadServiceTest {
	
		private LocalidadService service = null;
		
		public LocalidadServiceTest() {
			service = new LocalidadServiceImpl() ;
		}
		
		public void testFindByAll() throws DataException {
			List<Localidad> c = service.findByProvincia((long)27);
			for(Localidad provincia: c)
			System.out.println(provincia);
		}
		
		public void testFindByid() throws DataException {
			Localidad c = service.findById((long)2);
			System.out.println(c);
		}
		
		
		public static void main(String[] args) throws DataException {
			LocalidadServiceTest test = new LocalidadServiceTest() ;
			test.testFindByAll();
			test.testFindByid();		}
	}


