package es.flasheat.service;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Categoria;
import es.flasheat.util.DataException;

public interface CategoriaService {
	
	public Categoria findById(Long id, String idioma) throws DataException;
	
	public Categoria findByRestaurante(Long idRestaurante, String idioma) throws DataException;

	public List<Categoria> findAll(String idioma) throws DataException;


}
