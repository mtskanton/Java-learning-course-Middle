package app.example.service;

import app.example.exception.TransferException;
import app.example.model.Account;
import app.example.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accounts;

    public List<Account> getAccounts() {
        return this.accounts.getAll();
    }

    public void transfer(int from, int to, double sum) throws TransferException {
        this.accounts.transfer(from, to, sum);
    }
}
