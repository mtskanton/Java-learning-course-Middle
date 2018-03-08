package app.example.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Маппинг сущности Account.
 */
public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {

        Account account = new Account();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Double balance = rs.getDouble("balance");

        account.setId(id);
        account.setName(name);
        account.setBalance(balance);
        return account;
    }
}
