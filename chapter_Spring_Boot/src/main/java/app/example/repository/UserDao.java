package app.example.repository;

import app.example.model.User;
import app.example.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Dao класса User.
 */
@Repository
public class UserDao {

    private JdbcTemplate template;

    @Autowired
    UserDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public User getUserByLogin(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        Object[] params = new Object[]{email};
        try {
            return this.template.queryForObject(sql, params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<String> getUserRoles(int id) {
        String sql = "SELECT r.role FROM users_roles ur, roles r WHERE ur.role_id = r.id AND ur.user_id = ?";
        Object[] params = new Object[] {id};
        List<String> roles = this.template.queryForList(sql, params, String.class);
        return roles;
    }
}
