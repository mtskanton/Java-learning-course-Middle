package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование Paint.
 */
public class PaintTest {
    /**
     * Тест метода рисования пирамиды высотой 2.
     */
    @Test
    public void whenPyramidHeightTwoThenStringAsBelow() {
        Paint p = new Paint();
        String result = p.pyramid(2);
        String expected = " ^ \r\n^^^\r\n";
        assertThat(result, is(expected));
    }

    /**
     * Тест метода рисования пирамиды высотой 3.
     */
    @Test
    public void whenPyramidHeightThreeThenStringAsBelow() {
        Paint p = new Paint();
        String result = p.pyramid(3);
        String expected = "  ^  \r\n ^^^ \r\n^^^^^\r\n";
        assertThat(result, is(expected));
    }
}