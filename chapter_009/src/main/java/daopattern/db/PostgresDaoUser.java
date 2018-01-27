package daopattern.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import daopattern.entity.Address;
import daopattern.entity.Music;
import daopattern.entity.Role;
import daopattern.entity.User;

/**
 * CRUD сущности пользователь.
 * БД PostgreSQL
 */
public class PostgresDaoUser implements DaoEntity<User>, RepositoryUser {

    private Connection conn;

    PostgresDaoUser() {
        this.conn = PostgresConnection.INSTANCE.getConnection();
    }

    @Override
    public User getById(int id) {
        User user = null;

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            user = this.gainUser(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void create(User user) {
        PreparedStatement pst = null;
        try {

            Role role = user.getRole();
            Address address = user.getAddress();
            List<Music> music = user.getMusic();

            this.conn.setAutoCommit(false);

            pst = this.conn.prepareStatement("INSERT INTO addresses (address) VALUES (?)", new String[]{"id"});
            pst.setString(1, address.getAddress());
            pst.executeUpdate();
            ResultSet addressKey = pst.getGeneratedKeys();
            addressKey.next();
            int addressId = addressKey.getInt("id");

            pst = this.conn.prepareStatement("INSERT INTO users VALUES "
            + "(DEFAULT, ?, ?, ?, ?);", new String[] {"id"});
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setInt(3, role.getId());
            pst.setInt(4, addressId);
            pst.executeUpdate();

            ResultSet userKey = pst.getGeneratedKeys();
            userKey.next();
            int userId = userKey.getInt("id");

            this.addUserMusic(userId, music);

            this.conn.commit();
            this.conn.setAutoCommit(true);
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement pst = null;
        try {
            int id = user.getId();
            String login = user.getLogin();
            String password = user.getPassword();
            Role role = user.getRole();
            Address address = user.getAddress();
            List<Music> music = user.getMusic();

            this.conn.setAutoCommit(false);

            pst = this.conn.prepareStatement("UPDATE addresses SET address = ? WHERE id = ?");
            pst.setString(1, address.getAddress());
            pst.setInt(2, address.getId());
            pst.executeUpdate();

            pst = this.conn.prepareStatement("UPDATE users "
                    + "SET login = ?, password = ?, role_id = ? WHERE id = ?");
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setInt(3, role.getId());
            pst.setInt(4, id);
            pst.executeUpdate();

            pst = this.conn.prepareStatement("DELETE FROM user_music WHERE user_id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            this.addUserMusic(id, music);

            this.conn.commit();
            this.conn.setAutoCommit(true);
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        int id = user.getId();
        int addressId = user.getAddress().getId();
        try {
            PreparedStatement pst = this.conn.prepareStatement("DELETE FROM user_music WHERE user_id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            pst = this.conn.prepareStatement("DELETE FROM users WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            pst = this.conn.prepareStatement("DELETE FROM addresses WHERE id = ?");
            pst.setInt(1, addressId);
            pst.executeUpdate();

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsersByAddress(String address) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT u.id id, login, password, role_id, address_id FROM users u JOIN addresses a ON u.address_id=a.id WHERE address LIKE ?")) {
            pst.setString(1, "%" + address + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM users u JOIN roles r ON u.role_id=r.id WHERE r.id = ?")) {
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    /**
     * Сбор пользователя.
     * @param rs результат выборки из БД
     * @return собранного пользователя
     * @throws SQLException при ошибке во время SQL запроса
     */
    private User gainUser(ResultSet rs) throws SQLException {
        User user = new User();
        int id = rs.getInt("id");
        user.setId(id);
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(this.getUserRole(rs.getInt("role_id")));
        user.setAddress(this.getUserAddress(rs.getInt("address_id")));
        user.setMusic(this.getUserMusic(id));
        return user;
    }

    /**
     * Получить роль пользователя.
     * @param roleId id роли
     * @return роль
     * @throws SQLException при ошибке во время SQL запроса
     */
    private Role getUserRole(int roleId) throws SQLException {
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
        pst.setInt(1, roleId);
        ResultSet rs = pst.executeQuery();
        rs.next();

        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setRole(rs.getString("role"));

        pst.close();
        return role;
    }

    /**
     * Получить адрес пользователя.
     * @param addressId id адреса
     * @return адрес
     * @throws SQLException при ошибке во время SQL запроса
     */
    private Address getUserAddress(int addressId) throws SQLException {
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM addresses WHERE id = ?");
        pst.setInt(1, addressId);
        ResultSet rs = pst.executeQuery();
        rs.next();

        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setAddress(rs.getString("address"));

        rs.close();
        return address;
    }

    /**
     * Получить жанры музыки пользователя.
     * @param userId id пользователя
     * @return список жанров музыки
     * @throws SQLException при ошибке во время SQL запроса
     */
    private List<Music> getUserMusic(int userId) throws SQLException {
        PreparedStatement pst = this.conn.prepareStatement("SELECT id, music FROM user_music JOIN music ON user_music.music_id = music.id WHERE user_id = ?;");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();

        List<Music> list = new ArrayList<>();
        while (rs.next()) {
            Music music = new Music();
            music.setId(rs.getInt("id"));
            music.setMusic(rs.getString("music"));
            list.add(music);
        }

        rs.close();
        return list;
    }

    /**
     * Добавить список жанров музыки для пользователя.
     * @param id пользователя
     * @param music список жанров музыки
     * @throws SQLException при ошибке во время SQL запроса
     */
    private void addUserMusic(int id, List<Music> music) throws SQLException {
        PreparedStatement pst = null;
        for (Music m : music) {
            pst = this.conn.prepareStatement("INSERT INTO user_music VALUES (?, ?)");
            pst.setInt(1, id);
            pst.setInt(2, m.getId());
            pst.executeUpdate();
        }
    }
}
