package app.example.repository;

import java.util.List;

/**
 * Интерфейс DAO сущности.
 */
public interface IDao<T> {

    T getById(int id);

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}

