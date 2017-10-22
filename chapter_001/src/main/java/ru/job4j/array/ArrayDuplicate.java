package ru.job4j.array;
import java.util.Arrays;
/**
 * Удаление дубликатов в массиве.
 * @author Anton Matsik
 * @since 22.10.2017
 */
public class ArrayDuplicate {
    /**
     * Удалеяет дубликаты из массива.
     * @param array входной массив с дубликатами
     * @return массив без дубликатов
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}