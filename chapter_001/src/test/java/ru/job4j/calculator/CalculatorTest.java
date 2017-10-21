package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование класса Calculator.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class CalculatorTest {
    /**
     * Тестирование сложения.
     */
    @Test
    public void whenOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1d, 1d);
        double result = calc.getResult();
        double expected = 2d;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование вычитания.
     */
    @Test
    public void whenFiveMinusThreeThenTwo() {
        Calculator calc = new Calculator();
        calc.subtract(5d, 3d);
        double result = calc.getResult();
        double expected = 2d;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование умножения.
     */
    @Test
    public void whenFiveMultiplyByThreeThenFifteen() {
        Calculator calc = new Calculator();
        calc.multiple(5d, 3d);
        double result = calc.getResult();
        double expected = 15d;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование деления.
     */
    @Test
    public void whenTenDivideByFiveThenTwo() {
        Calculator calc = new Calculator();
        calc.div(10d, 5d);
        double result = calc.getResult();
        double expected = 2d;
        assertThat(result, is(expected));
    }
}
