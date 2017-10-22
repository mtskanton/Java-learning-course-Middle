package ru.job4j.array;

/**
 * Перевернуть массив.
 * @author Anton Matsik
 * @since 22.10.2017
 */
public class Turn {
    /**
     * Принимает массив и возвращает массив элементов в обратном порядке.
     * @param array входной массив
     * @return reversedArray с элементами в обратном порядке
     */
    public int[] back(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int reversedItem = (array.length - i - 1);
            reversedArray[reversedItem] = array[i];
        }
        return reversedArray;
    }
}
