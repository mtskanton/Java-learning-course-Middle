package ru.job4j.bank;

public class AccountNotFoundException extends RuntimeException {
    AccountNotFoundException(String msg) {
        super(msg);
    }
}
