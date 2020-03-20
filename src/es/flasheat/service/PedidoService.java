package es.flasheat.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import es.flasheat.model.Pedido;
import es.flasheat.util.DataException;

public interface PedidoService {

	public Pedido findById(Long id) throws DataException;

	public List<Pedido> findByCliente(Long idCliente)throws DataException;
	
	public List<Pedido> findByRestaurante(Long idRestaurante)throws DataException;

	public List<Pedido> findByFecha(Date fechaDesde, Date fechaHasta)throws DataException;

	public void insertar(Pedido p)throws DataException;
	
	public void insertarCarrito(Pedido p) throws DataException;

	public Pedido updateStatus(int status, Long id)throws DataException;
	
	public void delete(Long id)throws DataException;
	
}
