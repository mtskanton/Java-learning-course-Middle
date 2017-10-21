package ru.job4j.loop;

/**
 * Вычисление факториала.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Factorial {
    /**
     * Вычисление факториала заданного числа.
     * @param n входное число
     * @return факториал числа
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
