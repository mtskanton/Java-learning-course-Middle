package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Тест класса Subword.
 * @author Anton Matsik
 * @since 23.10.2017
 */
public class SubwordTest {
    /**
     * Если субстрока полностью входит в строку, тогда true.
     */
    @Test
    public void whenWordContannsSubwordThenTrue() {
        Subword s = new Subword();
        String origin = "привет";
        String sub = "иве";
        boolean result = s.contains(origin, sub);
        boolean expected = origin.contains(sub);
        assertThat(result, is(expected));
    }
}
