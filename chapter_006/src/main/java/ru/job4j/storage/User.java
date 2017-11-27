package ru.job4j.storage;

/**
 * Класс пользователя.
 */
public class User {
    private int id;
    private int amount;

    User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    /**
     * Операция снятия средств.
     * @param sum сумма для снятия
     * @return true если успешно
     */
    public boolean withdraw(int sum) {
        boolean result = false;
        if (this.amount >= sum) {
            this.amount = this.amount - sum;
            result = true;
        }
        return result;
    }

    /**
     * Операция пополнения счета.
     * @param sum сумма для пополнения
     */
    public void refill(int sum) {
        this.amount = this.amount + sum;
    }
}
