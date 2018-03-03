package carselling.logic;

import carselling.model.Carcase;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозитороий сущности кузов автомобиля.
 */
public interface RepositoryCarcase extends CrudRepository<Carcase, Integer> {

}
