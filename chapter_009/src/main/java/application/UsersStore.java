package application;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Класс взаимодействия с базой данных.
 * Синглтон
 * Используется apache connection pool без JNDI
 */
public class UsersStore {
    private Connection conn;
    private static UsersStore instance;
    private ComboPooledDataSource cpds;

    private UsersStore() {
        try {
            this.cpds = new ComboPooledDataSource();
            this.cpds.setDriverClass("org.postgresql.Driver");
            this.cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/user_storage");
            this.cpds.setUser("postgres");
            this.cpds.setPassword("password");
            this.cpds.setMaxPoolSize(20);

            this.conn = cpds.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized UsersStore getInstance() {
        if (instance == null) {
            instance = new UsersStore();
        }
        return instance;
    }

    /**
     * Метод получения списка пользователей.
     * @return список пользователей
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users JOIN roles ON users.role_id=roles.id")) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCreated(rs.getDate("created"));
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
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM users JOIN roles ON users.role_id = roles.id WHERE users.id=?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreated(rs.getDate("created"));
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
    public void addUser(String name, String login, String password, String email, String role) {
        try (PreparedStatement pst = this.conn.prepareStatement("INSERT INTO users (name, login, password, email, created, role_id) VALUES (?, ?, ?, ?, ?, (SELECT id FROM roles WHERE role=?));")) {
            pst.setString(1, name);
            pst.setString(2, login);
            pst.setString(3, password);
            pst.setString(4, email);
            java.util.Date d = new java.util.Date();
            Date dataTime = new java.sql.Date(d.getTime());
            pst.setDate(5, dataTime);
            pst.setString(6, role);
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
            //если это логин текущего пользователя, то пропускаем. Актуально при обновлении прочей информации пользвателя.
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
        try (PreparedStatement pst = this.conn.prepareStatement("UPDATE users SET name=?, login=?, password=?, email=?, role_id=(SELECT id FROM roles WHERE role=?) WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getRole());
            pst.setInt(6, user.getId());
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
     * Создание таблиц и заполенние пользователей.
     * Для демонстрационных целей и тестирования.
     */
    private void createTables() {
        try (Statement st = this.conn.createStatement()) {

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
                        + "created date DEFAULT now(),"
                        + "role_id int,"
                        + "CONSTRAINT roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)"
                        + ");");

            st.executeUpdate("CREATE INDEX users_login_idx ON users(login)");

            st.executeUpdate("INSERT INTO roles (role) VALUES ('User')");
            st.executeUpdate("INSERT INTO roles (role) VALUES ('Admin')");

            st.executeUpdate("INSERT INTO users (name, login, password, role_id) VALUES ('root', 'root', 'root', 2)");
            st.executeUpdate("INSERT INTO users (name, login, password, role_id) VALUES ('user', 'user', 'user', 1)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
