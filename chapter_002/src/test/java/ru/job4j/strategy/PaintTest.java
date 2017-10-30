package ru.job4j.strategy;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Класс тестирования реализации паттерна Стратегия.
 */
public class PaintTest extends OutputTest {
    /**
     * Тестирование рисования треугольника.
     */
    @Test
    public void whenShapeIsTriangleThenDrawTriangle() {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        String result = "   +   \n  +++  \n +++++ \n+++++++\n";
        assertThat(result, is(output.toString()));
    }

    /**
     * Тестирование рисования квадрата.
     */
    @Test
    public void whenShapeIsSquareThenDrawSquare() {
        Paint paint = new Paint();
        paint.draw(new Square());
        String result = "++++\n++++\n++++\n++++\n";
        assertThat(result, is(output.toString()));
    }
}
