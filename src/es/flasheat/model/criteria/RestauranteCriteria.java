package es.flasheat.model.criteria;

public class RestauranteCriteria {
	
	private Integer valoracion = null;
	private Long idLocalidad = null;
	private Long idProvincia = null;
	private Long categoria = null;
	private Boolean envioGratis = false;
	
	public RestauranteCriteria() {
	}
	
	public Integer getValoracion() {
		return valoracion;
	}
	public Long getLocalidad() {
		return idLocalidad;
	}
	public Long getCategoria() {
		return categoria;
	}
	public Boolean isEnvioGratis() {
		return envioGratis;
	}
	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}
	public void setLocalidad(Long localidad) {
		this.idLocalidad = localidad;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	public void setEnvioGratis(Boolean envioGratis) {
		this.envioGratis = envioGratis;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
}
