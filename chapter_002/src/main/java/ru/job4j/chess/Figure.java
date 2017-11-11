package ru.job4j.chess;

public abstract class Figure {
    final Cell position;
    Figure(Cell position) {
        this.position = position;
    }
    abstract Figure clone(Cell newPosition);
    abstract Cell[] way(Cell destination) throws ImpossibleMoveException;
    abstract boolean occupiedCell(Cell cell);
}

class Bishop extends Figure {

    Bishop(Cell position) {
        super(position);
    }

    /**
     * Метод проверки, стоит ли фигура на заданной клетке.
     * @param cell клетка для проверки
     * @return true если фигу ра на клетке
     */
    public boolean occupiedCell(Cell cell) {
        boolean result = false;
        if (position.x == cell.x && position.y == cell.y) {
            result = true;
        }
        return result;
    }

    /**
     * Клонирование объекта текщего класса с новой позицией.
     * @param destination новая клетка
     * @return объект текущего класса
     */
    public Bishop clone(Cell destination) {
        System.out.print(String.format("Figure moved to x:%s y:%s", destination.x, destination.y));
        return new Bishop(destination);
    }

    /**
     * Метод получения клеток на пути движения фигуры.
     * @param destination клетка финального положения
     * @return список клеток на пути
     * @throws ImpossibleMoveException выброс в случае, если неверно заданы координаты перемещения
     */
    public Cell[] way(Cell destination) throws ImpossibleMoveException {

        Cell[] cells = null;

        //Проверка правильности заданных координат
        boolean xValid = 1 <= destination.x && destination.x <= 8;
        boolean yValid = 1 <= destination.y && destination.y <= 8;
        //Проверка, что фигура может быть передвинута на указанную ячейку
        boolean wayValid = Math.abs(position.x - destination.x) == Math.abs(position.y - destination.y);

        if (!(xValid && yValid && wayValid)) {
            throw new ImpossibleMoveException("Impossible move!");
        } else {
            int steps = Math.abs(position.x - destination.x);
            cells = new Cell[steps];
            int x = position.x;
            int y = position.y;
            for (int i = 0; i < cells.length; i++) {
                if (destination.x > position.x) {
                    x++;
                } else {
                    x--;
                }
                if (destination.y > position.y) {
                    y++;
                } else {
                    y--;
                }
                cells[i] = new Cell(x, y);
            }
        }
        return cells;
    }
}