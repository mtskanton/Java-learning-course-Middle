package application;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
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
     * @throws SQLException в случае возникновения при SQL запросе
     */
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreated(rs.getDate("created"));

                users.add(user);
            }
        }

        return users;
    }

    /**
     * Метод получения пользователя по id.
     * @param id пользователя
     * @return класс пользователя
     * @throws SQLException в случае возникновения при SQL запросе
     */
    public User getUser(Integer id) throws SQLException {
        User user = new User();
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM users WHERE id=?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setCreated(rs.getDate("created"));
        }
        return user;
    }

    /**
     * Метод добавления пользователя.
     * @param name имя
     * @param login логин
     * @param email e-mail
     * @throws SQLException в случае возникновения при SQL запросе
     */
    public void addUser(String name, String login, String email) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name varchar(255) NOT NULL,"
                    + "login varchar(255) NOT NULL,"
                    + "email varchar(255) NOT NULL,"
                    + "created date DEFAULT now()"
                    + ");"
                    + "INSERT INTO users (name, login, email, created) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, name);
            pst.setString(2, login);
            pst.setString(3, email);
            java.util.Date d = new java.util.Date();
            Date dataTime = new java.sql.Date(d.getTime());
            pst.setDate(4, dataTime);
            pst.executeUpdate();
        }
    }

    /**
     * Метод обновления информации о пользователе
     * @param id id пользователя в БД
     * @param name новое имя
     * @param login новый логин
     * @param email новый e-mail
     * @throws SQLException в случае возникновения при SQL запросе
     */
    public void updateUser(User user) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setInt(4, user.getId());
            pst.executeUpdate();
        }
    }

    /**
     * Метод удаления пользователя из БД.
     * @param id записи в БД для удаления
     * @throws SQLException в случае возникновения при SQL запросе
     */
    public void deleteUser(Integer id) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}
