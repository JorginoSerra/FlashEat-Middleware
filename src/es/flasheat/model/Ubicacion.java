package es.flasheat.model;

public class Ubicacion extends AbstractValueObject  {
	
	private Long id = null;
	private String nombre = null;
	private Long idLocalidad = null;
	
	
	public Ubicacion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
}
