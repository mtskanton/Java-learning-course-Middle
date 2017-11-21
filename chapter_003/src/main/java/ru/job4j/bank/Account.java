package ru.job4j.bank;

/**
 * Класс аккаунта (счета) пользователя.
 */
public class Account {
    private double value;
    private final int requisites;

    Account() {
        this.value = 100; //в демонстрационных целях на счету сразу сумма
        this.requisites = (int) (Math.random() * 100000);
    }

    /**
     * Операция пополнения счета.
     * @param amount сумма
     */
    void refill(double amount) {
        this.value = this.value + amount;
    }

    /**
     * Операция снятия средств со счета.
     * @param amount сумма
     * @return true если запрошенная сумма не меньше имеющегося количества
     */
    boolean withdraw(double amount) {
        if (this.value >= amount) {
            this.value = this.value - amount;
            return true;
        }
        return false;
    }
}
