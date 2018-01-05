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
    private static Connection conn;
    private static UsersStore instance;
    private static ComboPooledDataSource cpds;

    private UsersStore() {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/user_storage");
            cpds.setUser("postgres");
            cpds.setPassword("password");
            cpds.setMaxPoolSize(20);

            conn = cpds.getConnection();
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
     * @throws SQLException
     */
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreated(rs.getDate("created"));

                users.add(user);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (st != null) {
                st.close();
            }
        }

        return users;
    }

    /**
     * Метод получения пользователя по id.
     * @param id пользователя
     * @return класс пользователя
     * @throws SQLException
     */
    public User getUser(Integer id) throws SQLException {
        User user = new User();
        PreparedStatement pst = null;

        pst = conn.prepareStatement("SELECT * FROM users WHERE id=?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setCreated(rs.getDate("created"));

        return user;
    }

    /**
     * Метод добавления пользователя.
     * @param name имя
     * @param login логин
     * @param email e-mail
     * @throws SQLException
     */
    public void addUser(String name, String login, String email) throws SQLException {
        PreparedStatement pst = null;
        try {


            pst = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name varchar(255) NOT NULL,"
                    + "login varchar(255) NOT NULL,"
                    + "email varchar(255) NOT NULL,"
                    + "created date DEFAULT now()"
                    + ");"
                    + "INSERT INTO users (name, login, email, created) VALUES (?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, login);
            pst.setString(3, email);
            java.util.Date d = new java.util.Date();
            Date dataTime = new java.sql.Date(d.getTime());
            pst.setDate(4, dataTime);
            pst.executeUpdate();

        } finally {
            if (pst !=  null) {
                pst.close();
            }
        }
    }

    /**
     * Метод обновления информации о пользователе
     * @param id id пользователя в БД
     * @param name новое имя
     * @param login новый логин
     * @param email новый e-mail
     * @throws SQLException
     */
    public void updateUser(Integer id, String name, String login, String email) throws SQLException {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?");
            pst.setString(1, name);
            pst.setString(2, login);
            pst.setString(3, email);
            pst.setInt(4, id);
            pst.executeUpdate();

        } finally {
            if (pst !=  null) {
                pst.close();
            }
        }
    }

    /**
     * Метод удаления пользователя из БД.
     * @param id записи в БД для удаления
     * @throws SQLException
     */
    public void deleteUser(Integer id) throws SQLException {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM users WHERE id=?");
            pst.setInt(1, id);
            pst.executeUpdate();

        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }
}
