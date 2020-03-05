package es.flasheat.service;

import java.util.List;

import es.flasheat.model.Restaurante;
import es.flasheat.model.criteria.RestauranteCriteria;
import es.flasheat.util.DataException;

public interface RestauranteService {

public Restaurante findById(Long id, String idioma) throws DataException;

public List<Restaurante> findByCriteria(RestauranteCriteria c, String idioma) throws DataException;

}
