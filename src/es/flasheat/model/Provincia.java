package es.flasheat.model;

public class Provincia  extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	private String idPais = null;
	
	public Provincia() {
	}
	
	public Long getCodProvincia() {
		return id;
	}
	public void setCodProvincia(Long codProvincia) {
		this.id = codProvincia;
	}
	public String getProvincia() {
		return nombre;
	}
	public void setProvincia(String provincia) {
		this.nombre = provincia;
	}
	public String getCodPais() {
		return idPais;
	}
	public void setCodPais(String codPais) {
		this.idPais = codPais;
	}
}
