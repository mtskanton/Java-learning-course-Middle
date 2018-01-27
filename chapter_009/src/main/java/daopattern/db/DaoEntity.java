package daopattern.db;

import java.util.List;

/**
 * Интерфейс сущности.
 */
public interface DaoEntity<T> {

    T getById(int id);

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
