package ru.job4j.loop;

/**
 * Подсчет суммы чётных чисел в диапазоне.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Counter {
    /**
     * Вычисление суммы четныx чисел в диапазоне от start до finish включительно.
     * @param start стартовое число диапазона
     * @param finish финальное число диапазона
     * @return сумма четных чисел
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 != 0) {
                continue;
            }
            sum = sum + i;
        }
        return sum;
    }
}
