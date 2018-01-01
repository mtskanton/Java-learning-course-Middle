package crudservlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Класс взаимодействия с базой данных.
 * Синглтон с Eager Initialization
 */
public class UsersStore {
    private static final UsersStore INSTANCE = new UsersStore();
    private static Connection conn;

     private UsersStore() {
         //подключение к БД
         try {
             Class.forName("org.postgresql.Driver");
             String url = "jdbc:postgresql://localhost:5432/user_storage";
             String user = "postgres";
             String pass = "password";

             conn = DriverManager.getConnection(url, user, pass);
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
         }
     }

     public synchronized static UsersStore getInstance() {
         return INSTANCE;
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
