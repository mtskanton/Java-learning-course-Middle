package daopattern.db;

import daopattern.entity.Role;
import daopattern.entity.User;

import java.util.List;

/**
 * Интерфейс репозитория для пользователя.
 */
public interface RepositoryUser {

    //поиск пользователей по адресу
    List<User> getByAddress(String address);

    //поиск пользователей по роли
    List<User> getByRole(Role role);
}
