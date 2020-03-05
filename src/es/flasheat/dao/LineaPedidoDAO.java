package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.LineaPedido;
import es.flasheat.util.DataException;

public interface LineaPedidoDAO {

	public List<LineaPedido> findByPedido(Connection connection, Long idPedido) throws DataException;
	public void insertar(Connection connection, LineaPedido lp) throws DataException;
	public void deleteByPedido(Connection connection, Long idPedido) throws DataException;
	
}
