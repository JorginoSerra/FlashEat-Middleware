package es.flasheat.dao;

import java.sql.Connection;
import java.util.List;

import es.flasheat.model.Restaurante;
import es.flasheat.model.criteria.RestauranteCriteria;
import es.flasheat.util.DataException;


public interface RestauranteDAO {

public Restaurante findById(Connection connection, Long id, String idioma) throws DataException;

public List<Restaurante> findByCriteria(Connection connection, RestauranteCriteria c, String idioma) throws DataException;

/*No se implementarán en esta aplicacion
public void insertar(Restaurante r);

public void update(Restaurante r);

public void delete(Long id);
*/

}
