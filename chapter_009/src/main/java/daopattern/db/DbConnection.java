package daopattern.db;

import java.sql.Connection;

/**
 * Интерфейс подключения к БД.
 */
public interface DbConnection {

    Connection getConnection();
}
