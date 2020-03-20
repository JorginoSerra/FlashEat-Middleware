package es.flasheat.model;

public class LineaPedido extends AbstractValueObject  {

	private Long idLineaPedido = null;
	private Long idPedido = null;
	private Long idProducto = null;
	private Double precio = 0.0d;
	private Integer cantidad = 0;
	
	public Long getIdLineaPedido() {
		return idLineaPedido;
	}
	public void setIdLineaPedido(Long idLineaPedido) {
		this.idLineaPedido = idLineaPedido;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
