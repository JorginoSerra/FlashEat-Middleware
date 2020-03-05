package es.flasheat.model;

public class Producto extends AbstractValueObject  {

	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private Long idRestaurante = null;
	private Double precio = null;
	
	
	public Producto(){
	}

	public String getNombre() {
		return nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
