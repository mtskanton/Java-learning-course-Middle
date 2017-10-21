package ru.job4j.max;

/**
 * Поиск максимальных значений.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Max {
    /**
     * Сравнение двух чисел.
     * @param first первое число
     * @param second второе число
     * @return максимальное из сравниваемых чисел
     */
    public int max(int first, int second) {
        return ((first > second) ? first : second);
    }

    /**
     * Выбор наибольшего из трех чисел.
     * @param first первое число
     * @param second второе число
     * @param third третье число
     * @return одно из внесенных на сравнение чисел
     */
    public int maxOfThree(int first, int second, int third) {
        int maxOne = max(first, second);
        int maxTwo = max(first, third);
        return max(maxOne, maxTwo);
    }
}
