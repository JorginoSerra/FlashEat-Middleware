package es.flasheat.model;

public class Categoria extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	
	public Categoria() {
	}
	public Categoria(Long primaryKey){
		this.id = primaryKey;
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
}
