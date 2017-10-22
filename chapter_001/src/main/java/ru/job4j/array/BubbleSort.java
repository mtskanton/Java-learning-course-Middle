package ru.job4j.array;

/**
 * Сортировка массива методом перестановки.
 * @author Anton Matsik
 * @since 22.10.2017
 */
public class BubbleSort {
    /**
     * Принимает массив и возвращает отсортированный в порядке увеличения.
     * @param array принимаемый на сортировку массив
     * @return отсортированный массив
     */
    public int[] sort(int[] array) {
        //проходим по массиву такое количество раз, сколько длина массива
        for (int a = array.length; a > 1; a--) {
            //проходим по массиву сначала до заданной позиции
            for (int b = 0; b < (a - 1); b++) {
                //сравниваем соседние значения попарно
                if (array[b + 1] < array[b]) {
                    int helper = array[b];
                    array[b] = array[b + 1];
                    array[b + 1] = helper;
                }
            }
        }
        return array;
    }
}