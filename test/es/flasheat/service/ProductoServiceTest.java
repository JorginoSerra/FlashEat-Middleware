package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Producto;
import es.flasheat.service.impl.ProductoServiceImpl;
import es.flasheat.util.DataException;

public class ProductoServiceTest {

	private ProductoService dao = null;
	
	public ProductoServiceTest() {
		dao = new ProductoServiceImpl() ;
	}
	
	public void testFindById() throws DataException {
		Producto producto = dao.findById((long) 1, "EN");
		System.out.println(producto);
	}
	
	public void testFindByRestaurante() throws DataException {
		List<Producto> productos = dao.findByRestaurante((long) 2, "ES");
		for (Producto p: productos) {
			System.out.println(p);
		}
	}
	
	public static void main(String[] args) throws DataException {
		ProductoServiceTest test = new ProductoServiceTest() ;
		test.testFindById();
		test.testFindByRestaurante();
		

	}

}

