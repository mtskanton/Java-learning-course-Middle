package storage.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import storage.model.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Реализация DAO интерфейса.
 * Jdbc подключение к БД
 * Сущность - пользователь
 */
public class JdbcStorage implements StorageDao<User> {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public User getById(int id) {
        String sql = "select * from Users where id = ?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from Users";
        return jdbcTemplateObject.query(sql, new UserMapper());
    }

    @Override
    public void create(User user) {
        String sql = "insert into Users (name, email) values (?, ?)";
        this.jdbcTemplateObject.update(sql, user.getName(), user.getEmail());
    }

    @Override
    public void update(User user) {
        String sql = "update User set name = ?, email = ? where id = ?";
        this.jdbcTemplateObject.update(sql, user.getName(), user.getEmail(), user.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Users where id = ?";
        this.jdbcTemplateObject.update(sql, id);
    }
}
