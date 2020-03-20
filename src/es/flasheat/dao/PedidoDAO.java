package es.flasheat.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import es.flasheat.model.Pedido;
import es.flasheat.util.DataException;

public interface PedidoDAO {

public Pedido findById(Connection connection, Long id) throws DataException;

public List<Pedido> findByCliente(Connection connection, Long idCliente)throws DataException;

public List<Pedido> findByRestaurante(Connection connection, Long idRestaurante)throws DataException;

public List<Pedido> findByFecha(Connection connection, Date fechaDesde, Date fechaHasta)throws DataException;

public void insertar(Connection connection, Pedido p)throws DataException;

public void insertarCarrito(Connection connection, Pedido p) throws DataException;

public Pedido updateStatus(Connection connection, int status, Long id)throws DataException;

public void delete(Connection connection, Long id)throws DataException;

// No se pueden eliminar pedidos. ?

}