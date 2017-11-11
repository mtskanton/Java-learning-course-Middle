package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest extends OutputTest {
    /**
     * Перемещение фигуры
     */
    @Test
    public void whenMoveFigureToX7Y7ThenNewCoordinatesX7Y7() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Board board = new Board();
        board.figures = new Bishop[1];
        board.figures[0] = new Bishop(new Cell(5, 5));

        Cell position = new Cell(5, 5);
        Cell destination = new Cell(7, 7);
        board.move(position, destination);
        assertThat("Figure moved to x:7 y:7", is(output.toString()));
    }

    /**
     * Ошибка: Фигура не найдена на заданной клетке
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenAskMovingButNoFigureThereThenException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Board board = new Board();
        board.figures = new Bishop[1];
        board.figures[0] = new Bishop(new Cell(5, 5));

        Cell position = new Cell(1, 1);
        Cell destination = new Cell(7, 7);
        board.move(position, destination);
    }

    /**
     * Ошибка: Неверные координаты
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenAskMovingToWrongCoordinatesThenException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Board board = new Board();
        board.figures = new Bishop[1];
        board.figures[0] = new Bishop(new Cell(5, 5));

        Cell position = new Cell(5, 5);
        Cell destination = new Cell(5, 7);
        board.move(position, destination);
    }

    /**
     * Ошибка: Другая фигура на пути движения
     */
    @Test(expected = OccupiedWayException.class)
    public void whenAskMovingButAnotherFigureOnTheWayThenException() throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Board board = new Board();
        board.figures = new Bishop[2];
        board.figures[0] = new Bishop(new Cell(5, 5));
        board.figures[1] = new Bishop(new Cell(3, 3));

        Cell position = new Cell(5, 5);
        Cell destination = new Cell(1, 1);
        board.move(position, destination);
    }
}
