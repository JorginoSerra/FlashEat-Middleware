package es.flasheat.model;

public class Pais  extends AbstractValueObject {
	private String id = null;
	private String nombre = null;


	public Pais() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
