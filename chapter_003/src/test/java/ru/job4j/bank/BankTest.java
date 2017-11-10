package ru.job4j.bank;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Класс тестирования функционала класса Bank.
 */
public class BankTest {
    /**
     * Добавление пользователя.
     */
    @Test
    public void whenAddUserThenItAddedIntoList() {
        Bank bank = new Bank();
        User user = new User("John", 123);
        bank.addUser(user);
        Map<User, List<Account>> list = bank.getBankData();
        boolean result = list.containsKey(user);
        assertThat(result, is(true));
    }

    /**
     * Удаление пользователя.
     */
    @Test
    public void whenDeleteUserThenItNoMoreInTheList() {
        Bank bank = new Bank();
        User user = new User("John", 123);
        bank.addUser(user);

        bank.deleteUser(user);
        Map<User, List<Account>> list = bank.getBankData();
        boolean result = list.containsKey(user);
        assertThat(result, is(false));
    }

    /**
     * Добавление счета пользователю.
     */
    @Test
    public void whenAddAccountThenItIsAccountList() {
        Bank bank = new Bank();
        User user = new User("John", 123);
        bank.addUser(user);
        Account account = new Account();
        bank.addAccountToUser(user, account);
        Map<User, List<Account>> list = bank.getBankData();
        List<Account> accounts = list.get(user);
        assertThat(accounts.get(1), is(account));
    }

    /**
     * Удаление счета пользователя.
     */
    @Test
    public void whenDeleteAccountThenItNoMoreInAccountList() {
        Bank bank = new Bank();
        User user = new User("John", 123);
        bank.addUser(user);
        Account account = new Account();
        bank.addAccountToUser(user, account);

        bank.deleteAccountFromUser(user, account);
        Map<User, List<Account>> list = bank.getBankData();
        List<Account> accounts = list.get(user);
        boolean contains = false;
        for (Account acc : accounts) {
            if (acc.equals(account)) {
                contains = true;
                break;
            }
        }
        assertThat(contains, is(false));
    }

    /**
     * Перечисление средств между счетами различных пользователей.
     */
    @Test
    public void whenTransferMoneyThenSumSubtractedFromUserAccountAndSameSumAddedToAnotherUserAccount() {
        Bank bank = new Bank();
        User user1 = new User("John", 123);
        User user2 = new User("Pete", 456);
        bank.addUser(user1);
        bank.addUser(user2);
        List<Account> accounts1 = bank.getUserAccounts(user1);
        List<Account> accounts2 = bank.getUserAccounts(user2);

        boolean transfer = bank.transferMoney(user1, accounts1.iterator().next(), user2, accounts2.iterator().next(), 50);
        assertThat(transfer, is(true));
    }

    /**
     * Перечисление средств между счетами одного пользователя.
     */
    @Test
    public void whenTransferMoneyThenSumSubtractedFromUserAccountAndSameSumAddedToSameUserAnotherAccount() {
        Bank bank = new Bank();
        User user = new User("John", 123);
        bank.addUser(user);
        bank.addAccountToUser(user, new Account());
        List<Account> accounts = bank.getUserAccounts(user);
        Account account1 = accounts.get(0);
        Account account2 = accounts.get(1);

        boolean transfer = bank.transferMoney(user, account1, user, account2, 50);
        assertThat(transfer, is(true));
    }
}

