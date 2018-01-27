package daopattern.db;

import daopattern.entity.Role;
import daopattern.entity.User;

import java.util.List;

/**
 * Интерфейс репозитория для пользователя.
 */
public interface RepositoryUser {

    //поиск пользователей по адресу
    List<User> getUsersByAddress(String address);

    //поиск пользователей по роли
    List<User> getUsersByRole(Role role);
}
