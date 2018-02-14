package storage.logic;

import java.util.List;

/**
 * Интерфейс DAO сущности.
 */
public interface StorageDao<T> {

    T getById(int id);

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(int id);
}
