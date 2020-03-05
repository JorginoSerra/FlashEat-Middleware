package es.flasheat.model;

public class Restaurante extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	private String telefono = null;
	private String email = null;
	private String contraseña = null;
	private double descuento = 0d;
	private double precioEnvio = 0d;
	private Long idUbicacion = null;
	private Long idCategoria = null;
	private String descripcion= null;
	private Double valoracion = null;
	
	public Restaurante(){
	}

	public Restaurante(Long primaryKey){
		this.id = primaryKey;
	}

	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public double getDescuento() {
		return descuento;
	}

	public double getPrecioEnvio() {
		return precioEnvio;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public void setPrecioEnvio(double precioEnvio) {
		this.precioEnvio = precioEnvio;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}



}
