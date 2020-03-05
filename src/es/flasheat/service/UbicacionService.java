package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Ubicacion;
import es.flasheat.util.DataException;

public interface UbicacionService {
	
	public Ubicacion findById(Long id) throws DataException;

	public List<Ubicacion> findByIds(List<Long> ids) throws DataException;	

	public void insertar(Ubicacion u) throws DataException;

	public void update(Ubicacion u) throws DataException;

	public void delete(Long id) throws DataException;


}
