package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на сортировку элементов методом перестановки.
 */
public class BubbleSortTest {
    /**
     * Обратный сортировка массива с числами от одного до пяти.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bs = new BubbleSort();
        int[] originalArray = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] resultArray = bs.sort(originalArray);
        int[] expectArray = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(resultArray, is(expectArray));
    }
}
