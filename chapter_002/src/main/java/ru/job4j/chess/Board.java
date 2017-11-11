package ru.job4j.chess;

public class Board {
    //набор фигур
    public Figure[] figures = null;
    private int number;

    //двигаем фигуру
    void move(Cell position, Cell destination) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {

        //находим, кто стоит на клетке
        boolean found = false;
        for (int  i = 0; i < figures.length; i++) {
            if (figures[i].occupiedCell(position)) {
                number = i;
                found = true;
            }
        }

        if (!found) {
            throw new FigureNotFoundException("Figure is not found!");
        }

        //уточняем клетки на пути перемещения
        Cell[] cells = figures[number].way(destination);

        //проверяем, что на этих клетках никто не стоит
        for (Cell cell : cells) {
            for (Figure figure : figures) {
                if (figure.occupiedCell(cell)) {
                    throw new OccupiedWayException("Occupied way!");
                }
            }
        }

        figures[number] = figures[number].clone(destination);
    }
}





