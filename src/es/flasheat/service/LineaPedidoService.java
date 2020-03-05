package es.flasheat.service;

import java.util.List;

import es.flasheat.model.LineaPedido;
import es.flasheat.util.DataException;

public interface LineaPedidoService {
	
	public List<LineaPedido> findByPedido(Long idPedido) throws DataException;
	public void insertar(LineaPedido lp) throws DataException;
	public void deleteByPedido(Long idPedido) throws DataException;


}
