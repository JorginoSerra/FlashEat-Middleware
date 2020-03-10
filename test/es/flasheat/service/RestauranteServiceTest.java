package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Restaurante;
import es.flasheat.model.criteria.RestauranteCriteria;
import es.flasheat.service.impl.RestauranteServiceImpl;
import es.flasheat.util.DataException;

public class RestauranteServiceTest {
	
private RestauranteService service = null;
	
	public RestauranteServiceTest() {
		service = new RestauranteServiceImpl() ;
	}
	
	public void testFindByAll() throws DataException {
		RestauranteCriteria cr = new RestauranteCriteria();
		cr.setIdProvincia((long)27);
		List<Restaurante> restaurantes = service.findByCriteria(cr, "ES");
		for (Restaurante r: restaurantes) {
			System.out.println(r);
		}
	}
	
	public void testFindById() throws DataException {
		Restaurante r = service.findById((long) 1, "ES");
			System.out.println(r);
		}

	public static void main(String[] args) throws DataException {
		RestauranteServiceTest test = new RestauranteServiceTest() ;
		test.testFindByAll();
		
	}
}
