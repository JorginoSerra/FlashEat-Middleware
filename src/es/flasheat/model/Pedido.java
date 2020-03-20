package es.flasheat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends AbstractValueObject  {

	private Long id = null;
	private double precio = 0d;
	private Date fecha = null;
	private String comentario = null;
	private int valoracion = 0;
	private Long idUsuario = null;
	private Long idRestaurante = null;
	private Integer idEstado = null;
	private String informacionExtra = null;
	
	private List<LineaPedido> lineas = null;
	
	
	public Pedido(){
		lineas = new ArrayList<LineaPedido>();
	}

	public Long getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getInformacionExtra() {
		return informacionExtra;
	}

	public void setInformacionExtra(String informacionExtra) {
		this.informacionExtra = informacionExtra;
	}

		
	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	
	public void addLinea(LineaPedido lp) {
		this.lineas.add(lp);
	}
	
	// remove? removeAllLineas?
}
