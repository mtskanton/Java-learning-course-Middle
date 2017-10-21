package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест рисования доски в псевдографике.
 */
public class BoardTest {
    /**
    * Тест рисования доски 3*3.
    */
    @Test
    public void whenBoardThreeToThreeThenStringAsBelow() {
        Board board = new Board();
        String result = board.paint(3, 3);
        String expected = "x x\r\n x \r\nx x\r\n";
        assertThat(result, is(expected));
    }

    /**
     * Тест рисования доски 5*4.
     */
    @Test
    public void whenBoardFiveToFourThenStringAsBelow() {
        Board board = new Board();
        String result = board.paint(5, 4);
        String expected = "x x x\r\n x x \r\nx x x\r\n x x \r\n";
        assertThat(result, is(expected));
    }

    /**
     * Тест рисования доски 3*3. Способ для ширины в нечетную величину.
     */
    @Test
    public void whenOddBoardThreeToThreeThenStringAsBelow() {
        Board board = new Board();
        String result = board.paintOddWidthBoard(3, 3);
        String expected = "x x\r\n x \r\nx x\r\n";
        assertThat(result, is(expected));
    }

    /**
     * Тест рисования доски 5*4. Способ для ширины в нечетную величину.
     */
    @Test
    public void whenOddBoardFiveToFourThenStringAsBelow() {
        Board board = new Board();
        String result = board.paintOddWidthBoard(5, 4);
        String expected = "x x x\r\n x x \r\nx x x\r\n x x \r\n";
        assertThat(result, is(expected));
    }
}