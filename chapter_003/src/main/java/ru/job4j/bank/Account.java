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
     * Операция изменения суммы на счете.
     * @param amount сумма изменения
     * @param operation тип операции
     * @return true если удачно выполнена
     */
    boolean changeValue(double amount, char operation) {
        boolean success = true;
        switch (operation) {
            case '+':
                this.value = this.value + amount;
                break;
            case '-':
                if (this.value >= amount) {
                    this.value = this.value - amount;
                } else {
                    success = false;
                }
                break;
            default:
                break;
        }
        return success;
    }
}
