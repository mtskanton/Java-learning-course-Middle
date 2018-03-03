package carselling.logic;

import carselling.model.Brand;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий сущности марка автомобиля.
 */
public interface RepositoryBrand extends CrudRepository<Brand, Integer> {

}
