package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Producto;
import es.flasheat.util.DataException;

public interface ProductoService {
	

public Producto findById(Long id, String idioma) throws DataException;

public List<Producto> findByRestaurante(Long idRestaurante, String idioma) throws DataException;


}
