package application.mvc.logic;

import application.mvc.model.User;
import application.mvc.model.UserMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class DaoUser {

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getAll() {
        final String sql = "select * from Users";
        return this.jdbcTemplate.query(sql, new UserMapper());
    }
}
