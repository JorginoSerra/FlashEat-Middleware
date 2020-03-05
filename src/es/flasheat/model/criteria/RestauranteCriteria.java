package es.flasheat.model.criteria;

public class RestauranteCriteria {
	
	private Integer valoracion = null;
	private Long idLocalidad = null;
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
	public void setProvincia(Long provincia) {
		this.idLocalidad = provincia;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	public void setEnvioGratis(Boolean envioGratis) {
		this.envioGratis = envioGratis;
	}
}
