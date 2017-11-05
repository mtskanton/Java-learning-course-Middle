package ru.job4j.chess;

public class Board {
    //набор фигур
    public Figure[] figures = null;
    private int numOfFigureOnCell;

    //двигаем фигуру
    void move(Cell source, Cell destination) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {

        //находим, кто стоит на клетке
        boolean figureFound = false;
        for (int  i = 0; i < figures.length; i++) {
            if (figures[i].occupiedCell(source)) {
                numOfFigureOnCell = i;
                figureFound = true;
            }
        }

        if (!figureFound) {
            throw new FigureNotFoundException("Figure is not found!");
        }

        //уточняем клетки на пути перемещения
        Cell[] cellsOnTheWay = figures[numOfFigureOnCell].way(destination);

        //проверяем, что на этих клетках никто не стоит
        for (Cell cell : cellsOnTheWay) {
            for (Figure figure : figures) {
                if (figure.occupiedCell(cell)) {
                    throw new OccupiedWayException("Occupied way!");
                }
            }
        }

        figures[numOfFigureOnCell] = figures[numOfFigureOnCell].clone(destination);
    }

    public static void main(String[] args) {
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
    }
}





