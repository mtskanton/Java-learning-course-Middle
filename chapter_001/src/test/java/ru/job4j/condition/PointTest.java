package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PointTest.
 * @author Anton Matsik
 * @since 21.01.2017
 */
public class PointTest {
    /**
     * Тестирование метода is.
     */
    @Test
    public void whenPointMatchCurveThenTrue() {
        Point p = new Point(1, 1);
        boolean result = p.is(0, 1);
        assertThat(result, is(true));
    }
}
