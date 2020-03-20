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
		lp.setPrecio(66.2);
		lp.setCantidad(10);
		p.setFecha(new Date());
		p.setIdRestaurante((long)1);
		p.setPrecio(66.2);
		p.addLinea(lp);
		dao.insertarCarrito(p);
		System.out.println(p.getId());
	}
	public static void main(String[] args) throws DataException {
		PedidoServiceTest test = new PedidoServiceTest() ;
		test.testInsert();

	}
}
