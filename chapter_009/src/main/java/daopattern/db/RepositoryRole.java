package daopattern.db;

import daopattern.entity.Role;

import java.util.List;

/**
 * Интерфейс репозитория для роли.
 */
public interface RepositoryRole {

    //поиск всех связанных с ролью сущностей
    List getRoleRelatedUsers(Role role);
}
