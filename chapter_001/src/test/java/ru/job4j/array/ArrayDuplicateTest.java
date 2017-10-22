package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

/**
 * Тестирование на удаление дубликатов из массива.
 */
public class ArrayDuplicateTest {
    /**
     * Тестирование на удаление дубликатов в массиве.
     */
    @Test
    public void whenArrayWithDuplicatesThenWithout() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] originArray = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] resultArray = ad.remove(originArray);
        String[] expectArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, arrayContainingInAnyOrder(expectArray));
    }
}