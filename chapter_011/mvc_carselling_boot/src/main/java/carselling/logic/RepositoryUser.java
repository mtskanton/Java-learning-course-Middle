package carselling.logic;

import carselling.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий сущности пользователь.
 */
public interface RepositoryUser extends CrudRepository<User, Integer> {

    User findByLogin(String login);
}
