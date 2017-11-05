package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest extends OutputTest {
    /**
     * Перемещение фигуры
     */
    @Test
    public void whenMoveFigureToX7Y7ThenNewCoordinatesX7Y7() {
        Board board = new Board();
        board.figures = new Slon[1];
        board.figures[0] = new Slon(new Cell(5, 5));

        Cell sourceCell = new Cell(5, 5);
        Cell moveToCell = new Cell(7, 7);

        try {
            board.move(sourceCell, moveToCell);
        } catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            System.out.println(e);
        }
        assertThat("Figure moved to x:7 y:7", is(output.toString()));
    }

    /**
     * Ошибка: Фигура не найдена на заданной клетке
     */
    @Test
    public void whenAskMovingButNoFigureThereThenException() {
        Board board = new Board();
        board.figures = new Slon[1];
        board.figures[0] = new Slon(new Cell(5, 5));

        Cell sourceCell = new Cell(1, 1);
        Cell moveToCell = new Cell(7, 7);

        try {
            board.move(sourceCell, moveToCell);
        } catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            System.out.println(e);
        }
        assertThat("ru.job4j.chess.FigureNotFoundException: Figure is not found!\r\n", is(output.toString()));
    }

    /**
     * Ошибка: Неверные координаты
     */
    @Test
    public void whenAskMovingToWrondCoordinatesThenException() {
        Board board = new Board();
        board.figures = new Slon[1];
        board.figures[0] = new Slon(new Cell(5, 5));

        Cell sourceCell = new Cell(5, 5);
        Cell moveToCell = new Cell(5, 7);

        try {
            board.move(sourceCell, moveToCell);
        } catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            System.out.println(e);
        }
        assertThat("ru.job4j.chess.ImpossibleMoveException: Impossible move!\r\n", is(output.toString()));
    }

    /**
     * Ошибка: Другая фигура на пути движения
     */
    @Test
    public void whenAskMovingButAnotherFigureOnTheWayThenException() {
        Board board = new Board();
        board.figures = new Slon[2];
        board.figures[0] = new Slon(new Cell(5, 5));
        board.figures[1] = new Slon(new Cell(3, 3));

        Cell sourceCell = new Cell(5, 5);
        Cell moveToCell = new Cell(1, 1);

        try {
            board.move(sourceCell, moveToCell);
        } catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            System.out.println(e);
        }
        assertThat("ru.job4j.chess.OccupiedWayException: Occupied way!\r\n", is(output.toString()));
    }
}
