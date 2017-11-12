package ru.job4j.coins;

public class CoffeeMachine {

    int[] changes(int value, int price) {
        int[] coins = {10, 5, 2, 1}; //массив доступных монет (по условию)
        int[] amount = new int[4]; //массив с количеством каждой монеты
        int[] change = new int[0]; //массив для каждой монетки (решение)
        int sum = value - price; //сумма сдачи
        int number = 0; //общее количество монет на сдачу

        if (sum <= 0) {
            System.out.println("No coins for you!");
        } else {

            for (int i = 0; i < coins.length; i++) {
                amount[i] = (sum / coins[i]);
                number += amount[i];
                sum = sum % coins[i];
            }

            change = new int[number];
            int k = 0;

            for (int i = 0; i < amount.length; i++) {
                //если сдача монет такого номинала предполагается
                if (amount[i] != 0) {
                    for (int j = 0; j < amount[i]; j++) {
                        change[k++] = coins[i];
                    }
                }
            }
        }
        return change;
    }
}
