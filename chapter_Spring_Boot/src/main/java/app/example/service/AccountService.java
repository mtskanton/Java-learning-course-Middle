package app.example.service;

import app.example.model.Account;
import app.example.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountDao accounts;

    public Account getAccount(int id) {
        return this.accounts.getById(id);
    }

}
