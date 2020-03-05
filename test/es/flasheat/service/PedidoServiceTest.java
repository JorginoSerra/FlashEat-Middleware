package es.flasheat.service;

import java.util.Date;

import es.flasheat.model.LineaPedido;
import es.flasheat.model.Pedido;
import es.flasheat.service.impl.PedidoServiceImpl;
import es.flasheat.util.DataException;

public class PedidoServiceTest {
	private PedidoService dao = null;
	
	public PedidoServiceTest() {
		dao = new PedidoServiceImpl() ;
	}
	public void testFindById() throws DataException {
		Pedido pedido = dao.findById((long) 23);
		System.out.println(pedido);
	}
	
	public void testUpdateStatus() throws DataException {
		dao.updateStatus(1, (long)10);
		Pedido pedido = dao.findById((long) 10);
		System.out.println(pedido);
	}
	
	public void testInsert() throws DataException {
		Pedido p = new Pedido();
		LineaPedido lp = new LineaPedido();
		lp.setIdProducto((long)2);
		lp.setPrecio(12.2);
		p.setIdUsuario((long)2);
		p.setIdRestaurante((long)1);
		p.setInformacionExtra("de refresco una cocacola");
		p.setFecha(new Date());
		p.setPrecio(10.20);
		p.addLinea(lp);
		dao.insertar(p);
		System.out.println(p.getId());
	}
	public static void main(String[] args) throws DataException {
		PedidoServiceTest test = new PedidoServiceTest() ;
		test.testInsert();
		test.testFindById();
		test.testUpdateStatus();

	}
}
