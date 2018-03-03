package carselling.logic;

import carselling.model.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий сущности Объявление.
 */
@Repository
public interface RepositoryAdvertisement extends CrudRepository<Advertisement, Integer> {

}