package ru.job4j.bank;

import java.util.*;

/**
 * Класс Банк.
 * Операции по работе с клиентами и счетами
 */
public class Bank {
    private Map<User, List<Account>> database = new HashMap<>();

    /**
     * Коллекция пользователей и списка их аккаунтов.
     * @return коллекцию
     */
    Map<User, List<Account>> getBankData() {
        return this.database;
    }

    /**
     * Добавить пользователя.
     * Аккаунт назначается по умолчанию
     * @param user пользователь
     */
    void addUser(User user) {
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(new Account());
        database.put(user, accounts);
    }

    /**
     * Удалить пользователя.
     * @param user пользователь
     */
    void deleteUser(User user) {
        database.remove(user);
    }

    /**
     * Назначить аккаунт для пользователя.
     * @param user пользователь
     * @param account аккаунт
     */
    void addAccountToUser(User user, Account account) {
        List<Account> accounts = database.get(user);
        accounts.add(account);
        database.put(user, accounts);
    }

    /**
     * Удалить аккаунт пользователя.
     * @param user пользователь
     * @param account аккаунт
     */
    void deleteAccountFromUser(User user, Account account) {
        List<Account> accounts = database.get(user);
        accounts.remove(account);
        database.put(user, accounts);
    }

    /**
     * Получить аккаунты пользователя.
     * @param user пользователь
     * @return список аккаунтов
     */
    List<Account> getUserAccounts(User user) {
        return database.get(user);
    }

    /**
     * Сделать перевод средств между аккаунтами пользователей
     * @param srcUser пользователь - отправитель
     * @param srcAccount аккаунт для списания
     * @param destUser пользователь - получатель
     * @param destAccount аккаунт для пополнения
     * @param amount сумма
     * @return true если перевод выполнен успешно
     */
    boolean transferMoney(User srcUser, Account srcAccount, User destUser, Account destAccount, double amount) {
        boolean success = false;
        Account source = null;
        Account destination = null;

        List<Account> srcAccounts = database.get(srcUser);
        for (Account src : srcAccounts) {
            if (src.equals(srcAccount)) {
                source = src;
                break;
            }
        }

        List<Account> destAccounts = database.get(destUser);
        for (Account dest : destAccounts) {
            if (dest.equals(destAccount)) {
                destination = dest;
                break;
            }
        }

        if (source != null && destination != null) {
            success = source.changeValue(amount, '-');
            if (success) {
                destination.changeValue(amount, '+');
            }
        }
        return success;
    }
}
