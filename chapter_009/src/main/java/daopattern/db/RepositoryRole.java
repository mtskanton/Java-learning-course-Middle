package daopattern.db;

import daopattern.entity.Role;
import java.util.Collection;

/**
 * Интерфейс репозитория для роли.
 */
public interface RepositoryRole {

    //поиск всех связанных с ролью сущностей
    Collection getRelatedUsers(Role role);
}
