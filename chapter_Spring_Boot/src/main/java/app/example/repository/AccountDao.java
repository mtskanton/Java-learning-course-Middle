package app.example.repository;

import app.example.model.Account;
import app.example.model.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * DAO сущности Account.
 */
@Repository
@Transactional
public class AccountDao implements IDao<Account> {

    private JdbcTemplate template;

    @Autowired
    AccountDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Account getById(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        Object[] params = new Object[] {id};
        Account result = this.template.queryForObject(sql, params, new AccountMapper());
        return result;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void create(Account entity) {

    }

    @Override
    public void update(Account entity) {

    }

    @Override
    public void delete(Account entity) {

    }
}
