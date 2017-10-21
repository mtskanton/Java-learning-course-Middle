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
     * Тестирование выбора наибольшего числа.
     */
    @Test
    public void whenFirstGreaterThanSecondThenFirst() {
        Max maximum = new Max();
        int result = maximum.max(7, 3);
        int expected = 7;
        assertThat(result, is(expected));
    }
}
