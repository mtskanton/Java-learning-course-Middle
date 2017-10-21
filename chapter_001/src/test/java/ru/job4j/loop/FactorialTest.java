package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест расчета факториала заданного числа.
 */
public class FactorialTest {
    /**
     * Вычисление факториала пяти.
     */
    @Test
    public void whenFactorialOfFiveThenOneHundredTwenty() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }
}