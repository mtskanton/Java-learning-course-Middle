package application;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Синглтон взаимодействия с базой данных.
 * Используется apache connection pool без JNDI
 */
public enum DbManager {

    INSTANCE;

    private Connection conn;

    private DbManager() {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/user_storage");
            cpds.setUser("postgres");
            cpds.setPassword("password");
            cpds.setMaxPoolSize(20);

            this.conn = cpds.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод получения списка пользователей.
     * @return список пользователей
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.id id, u.name username, login, password, email, created, countries.name country, cities.name city, role"
                    + " FROM users AS u JOIN roles ON u.role_id=roles.id "
                    + " LEFT JOIN countries ON u.country_id=countries.id "
                    + " LEFT JOIN cities ON u.city_id=cities.id")) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCreated(rs.getDate("created"));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(users);
        return users;
    }

    /**
     * Метод получения пользователя по id.
     * @param id пользователя
     * @return класс пользователя
     */
    public User getUser(Integer id) {
        User user = new User();
        try (PreparedStatement pst = this.conn.prepareStatement(
                "SELECT u.id id, u.name username, login, password, email, created, countries.name country, cities.name city, role "
                        + " FROM users u JOIN roles ON u.role_id = roles.id "
                        + " LEFT JOIN countries ON u.country_id=countries.id "
                        + " LEFT JOIN cities ON u.city_id=cities.id WHERE u.id=?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("username"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreated(rs.getDate("created"));
            user.setCountry(rs.getString("country"));
            user.setCity(rs.getString("city"));
            user.setRole(rs.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Метод добавления пользователя.
     * @param name имя
     * @param login логин
     * @param email e-mail
     */
    public void addUser(String name, String login, String password, String email,  String country, String city, String role) {
        try (PreparedStatement pst = this.conn.prepareStatement(
                "INSERT INTO users (name, login, password, email, country_id, city_id, created, role_id) "
                        + "VALUES (?, ?, ?, ?, (SELECT id FROM countries WHERE name=?), (SELECT id FROM cities WHERE name=?), ?, (SELECT id FROM roles WHERE role=?));")) {
            pst.setString(1, name);
            pst.setString(2, login);
            pst.setString(3, password);
            pst.setString(4, email);
            pst.setString(5, country);
            pst.setString(6, city);
            java.util.Date d = new java.util.Date();
            Date dataTime = new java.sql.Date(d.getTime());
            pst.setDate(7, dataTime);
            pst.setString(8, role);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка на занятость логина.
     * @param login логин для проверки
     * @return true если логин не занят
     */
    public boolean loginIsFree(int id, String login) {
        boolean free = true;
        for (User user :this.getUsers()) {
            //если это логин текущего пользователя, то пропускаем.
            // Актуально при обновлении прочей информации пользователя.
            if (user.getId() == id) {
                continue;
            }
            if (user.getLogin().equals(login)) {
                free = false;
                break;
            }
        }
        return free;
    }

    /**
     * Метод обновления информации о пользователе
     * @param id id пользователя в БД
     * @param name новое имя
     * @param login новый логин
     * @param email новый e-mail
     */
    public void updateUser(User user) {
        try (PreparedStatement pst = this.conn.prepareStatement(
            "UPDATE users SET name=?, login=?, password=?, email=?, country_id=(SELECT id FROM countries WHERE name=?), city_id=(SELECT id FROM cities WHERE name=?), role_id=(SELECT id FROM roles WHERE role=?) WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getCountry());
            pst.setString(6, user.getCity());
            pst.setString(7, user.getRole());
            pst.setInt(8, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод удаления пользователя из БД.
     * @param id записи в БД для удаления
     */
    public void deleteUser(Integer id) {
        try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers() {
        try (Statement st = this.conn.createStatement()) {
            st.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод получения списка ролей для переданного списка пользователей.
     * @return список ролей
     */
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM roles");
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole(rs.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    /**
     * Проверка учетных данных пользователя при входе в систему.
     * @param login логин
     * @param password пароль
     * @return User если пользователь зарегистрирован, возвращаем его
     */
    public User isRegistered(String login, String password) {
        User user = null;
        for (User u : this.getUsers()) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        return user;
    }

    /**
     * Получение списка стран.
     * @return список стран
     */
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM countries")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    /**
     * Получение списка городов.
     * @return список стран
     */
    public List<City> getCities() {
        List<City> cities = new ArrayList();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM cities")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Получение списка городов на запрос по стране.
     * @param country название страны
     * @return список городов
     */
    public List<City> getCitiesForCountry(String country) {
        List<City> cities = new ArrayList();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM cities JOIN countries ON cities.country_id=countries.id WHERE countries.name = ?")) {
            st.setString(1, country);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Создание таблиц и заполенние пользователей.
     * Для демонстрационных целей и тестирования
     */
    private void createTables() {
        try (Statement st = this.conn.createStatement()) {

            st.executeUpdate("DROP TABLE users; DROP TABLE roles; DROP TABLE cities; DROP TABLE countries;");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS roles ("
                    + "id SERIAL PRIMARY KEY,"
                    + "role varchar(25) CHECK (role IN ('User', 'Admin'))"
                    + ");");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                        + "id SERIAL PRIMARY KEY,"
                        + "name varchar(255) NOT NULL,"
                        + "login varchar(255) NOT NULL,"
                        + "password varchar(255) NOT NULL,"
                        + "email varchar(255),"
                        + "country_id int,"
                        + "city_id int,"
                        + "created date DEFAULT now(),"
                        + "role_id int,"
                        + "CONSTRAINT roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)"
                        + ");");

            st.executeUpdate("CREATE INDEX users_login_idx ON users(login)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS countries ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name varchar(255)"
                    + ");");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS cities ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name varchar(255),"
                    + "country_id int,"
                    + "CONSTRAINT countries_country_id_fk FOREIGN KEY (country_id) REFERENCES countries(id)"
                    + ");");

            st.executeUpdate("INSERT INTO countries (name) VALUES ('Russia')");
            st.executeUpdate("INSERT INTO countries (name) VALUES ('Germany')");

            st.executeUpdate("INSERT INTO cities (name, country_id) VALUES ('Moscow', 1)");
            st.executeUpdate("INSERT INTO cities (name, country_id) VALUES ('Samara', 1)");
            st.executeUpdate("INSERT INTO cities (name, country_id) VALUES ('Berlin', 2)");
            st.executeUpdate("INSERT INTO cities (name, country_id) VALUES ('Bremen', 2)");

            st.executeUpdate("INSERT INTO roles (role) VALUES ('User')");
            st.executeUpdate("INSERT INTO roles (role) VALUES ('Admin')");

            st.executeUpdate("INSERT INTO users (name, login, password, country_id, city_id, role_id) VALUES ('root', 'root', 'root', 1, 1, 2)");
            st.executeUpdate("INSERT INTO users (name, login, password, role_id) VALUES ('user', 'user', 'user', 1)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
