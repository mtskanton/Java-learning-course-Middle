package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование класса Max.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class MaxTest {
    /**
     * Тестирование выбора наибольшего числа из ДВУХ.
     */
    @Test
    public void whenFirstGreaterThanSecondThenFirst() {
        Max maximum = new Max();
        int result = maximum.max(7, 3);
        int expected = 7;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование выбора наибольшего числа из ТРЕХ.
     */
    @Test
    public void whenFirstGreaterThanOthersThenFirst() {
        Max maximum = new Max();
        int result = maximum.maxOfThree(10, 5, 9);
        int expected = 10;
        assertThat(result, is(expected));
    }
}
