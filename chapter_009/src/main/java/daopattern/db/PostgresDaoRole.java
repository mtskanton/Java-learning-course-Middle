package daopattern.db;

import daopattern.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD сущности роль.
 * БД PostgreSQL
 */
public class PostgresDaoRole implements DaoEntity<Role>, RepositoryRole {

    private Connection conn;

    PostgresDaoRole() {
        this.conn = PostgresConnection.INSTANCE.getConnection();
    }

    @Override
    public Role getById(int id) {
        Role role = null;
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            role = new Role();
            role.setId(id);
            role.setRole(rs.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Statement st = this.conn.createStatement()) {
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

    @Override
    public void create(Role role) {
        try (PreparedStatement pst = this.conn.prepareStatement("INSERT INTO roles (role) VALUES (?)")) {
            pst.setString(1, role.getRole());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try (PreparedStatement pst = this.conn.prepareStatement("UPDATE roles SET role = ? WHERE id = ?")) {
            pst.setString(1, role.getRole());
            pst.setInt(2, role.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Role role) {
        int id = role.getId();
        if (!this.isUsed(id)) {
            try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM roles WHERE id = ?")) {
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //пример возможной реализации
    @Override
    public List<String[]> getRelatedUsers(Role role) {
        List<String[]> list = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT role, login, password, address FROM roles "
                + "LEFT JOIN users ON roles.id=users.role_id "
                + "LEFT JOIN addresses ON users.address_id=addresses.id "
                + "WHERE roles.id = ?;")) {
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String[] result = new String[4];
                result[0] = rs.getString("role");
                result[1] = rs.getString("login");
                result[2] = rs.getString("password");
                result[3] = rs.getString("address");
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Проверка на то, что роль используется пользователем.
     * @param role роль
     * @return true, если используется
     */
    private boolean isUsed(int role) {
        boolean result = false;
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM users WHERE role_id = ?")) {
            pst.setInt(1, role);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
