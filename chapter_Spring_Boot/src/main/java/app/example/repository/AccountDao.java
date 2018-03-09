package app.example.repository;

import app.example.model.Account;
import app.example.model.AccountMapper;
import app.example.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
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
        try {
            Account result = this.template.queryForObject(sql, params, new AccountMapper());
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Account> getAll() {
        String sql = "SELECT * FROM account ORDER BY id";
        return this.template.query(sql, new AccountMapper());
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

//    MANDATORY Support a current transaction, throw an exception if none exists.
//    @Transactional(propagation = Propagation.MANDATORY)
    public void changeBalance(int id, double sum) throws TransferException {
        Account account = this.getById(id);
        if(account == null) {
            throw new TransferException("Не существует аккаунта с id " + id);
        }
        double newBalance = account.getBalance() + sum;
        if(newBalance < 0) {
            throw new TransferException("Баланс не может быть отрицательным");
        }
        String sql = "UPDATE account SET balance = ? WHERE id = ?";
        this.template.update(sql, newBalance, id);
    }

//    REQUIRES_NEW Create a new transaction, suspend the current transaction if one exists.
//    rollback всей транзакции если исключение TransferException.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransferException.class)
    public void transfer(int fromId, int toId, double sum) throws TransferException {
        changeBalance(fromId, -sum);
        changeBalance(toId, sum);
    }
}
