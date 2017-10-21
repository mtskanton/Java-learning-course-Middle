package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Counter class test.
 * @author  Anton Matsik
 * @since 21.10.2017
 */
public class CounterTest {
    /**
     * Test addition of even numbers in the range.
     */
    @Test
    public void whenCountAllEvenBetweenTwoAndTenThenThirty() {
        Counter count = new Counter();
        int result = count.add(2, 10);
        int expected = 30;
        assertThat(result, is(expected));
    }
}
