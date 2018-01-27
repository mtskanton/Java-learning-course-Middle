package daopattern.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Синглтон подключения к СУБД Postgresql.
 */
public enum PostgresConnection implements DbConnection {

    INSTANCE;

    private Connection connection;

    PostgresConnection() {
        try {
            Properties properties = this.getProperties();
            String url = properties.getProperty("url");
            String username = properties.getProperty("user");
            String password = properties.getProperty("password");

            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl(url);
            cpds.setUser(username);
            cpds.setPassword(password);
            cpds.setMaxPoolSize(20);

            this.connection = cpds.getConnection();

            this.createTables();

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка установочных параметров подключения.
     * @return параметры
     */
    private Properties getProperties() {
        Properties properties = null;
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("dbConnection.properties")) {
            Reader reader = new InputStreamReader(is);
            properties = new Properties();
            properties.load(reader);

        }  catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Получение подключения.
     * @return подключение к БД
     */
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Создание и заполнение таблиц БД.
     * Для демонстрационных целей и тестирования
     */
    public void createTables() {
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate("DROP TABLE user_music; DROP TABLE music; DROP TABLE users; DROP TABLE roles; DROP TABLE addresses;");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS roles ("
                    + "id SERIAL PRIMARY KEY,"
                    + "role VARCHAR(255)"
                    + ")");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS addresses ("
                    + "id SERIAL PRIMARY KEY,"
                    + "address VARCHAR(255)"
                    + ")");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS music ("
                    + "id SERIAL PRIMARY KEY,"
                    + "music VARCHAR(255)"
                    + ")");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "login VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "role_id INTEGER,"
                    + "address_id INTEGER,"
                    + "CONSTRAINT roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id),"
                    + "CONSTRAINT addresses_address_id_fk FOREIGN KEY (address_id) REFERENCES addresses(id)"
                    + ")");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS user_music ("
                    + "user_id INTEGER NOT NULL,"
                    + "music_id INTEGER NOT NULL,"
                    + "CONSTRAINT users_user_id_fk FOREIGN KEY (user_id) REFERENCES users(id),"
                    + "CONSTRAINT music_music_id_fk FOREIGN KEY (music_id) REFERENCES music(id)"
                    + ")");

            st.executeUpdate("INSERT INTO roles (role) VALUES ('ADMIN');");
            st.executeUpdate("INSERT INTO roles (role) VALUES ('USER');");

            st.executeUpdate("INSERT INTO addresses (address) VALUES ('Moscow, Mira, 3');");
            st.executeUpdate("INSERT INTO addresses (address) VALUES ('Samara, Lesnaya, 7');");
            st.executeUpdate("INSERT INTO addresses (address) VALUES ('Latvia, Pendulum, 34');");

            st.executeUpdate("INSERT INTO music (music) VALUES ('ROCK');");
            st.executeUpdate("INSERT INTO music (music) VALUES ('RAP');");
            st.executeUpdate("INSERT INTO music (music) VALUES ('POP');");

            st.executeUpdate("INSERT INTO users VALUES (DEFAULT, 'root', 'root', 1, 1);");
            st.executeUpdate("INSERT INTO users VALUES (DEFAULT, 'user', 'user', 2, 2);");
            st.executeUpdate("INSERT INTO users VALUES (DEFAULT, 'third', 'third', 2, 3);");

            st.executeUpdate("INSERT INTO user_music (user_id, music_id) VALUES (1, 1);");
            st.executeUpdate("INSERT INTO user_music (user_id, music_id) VALUES (2, 2);");
            st.executeUpdate("INSERT INTO user_music (user_id, music_id) VALUES (2, 3);");
            st.executeUpdate("INSERT INTO user_music (user_id, music_id) VALUES (3, 1);");
            st.executeUpdate("INSERT INTO user_music (user_id, music_id) VALUES (3, 3);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
