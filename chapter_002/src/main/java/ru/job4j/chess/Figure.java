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

class Slon extends Figure {

    Slon(Cell position) {
        super(position);
    }

    /**
     * Метод проверки, стоит ли фигура на заданной клетке.
     * @param cell клетка для проверки
     * @return true если фигу ра на клетке
     */
    public boolean occupiedCell(Cell cell) {
        boolean result = false;
        if (position.xCord == cell.xCord && position.yCord == cell.yCord) {
            result = true;
        }
        return result;
    }

    /**
     * Клонирование объекта текщего класса с новой позицией.
     * @param newPosition новая клетка
     * @return объект текущего класса
     */
    public Slon clone(Cell newPosition) {
        System.out.print(String.format("Figure moved to x:%s y:%s", newPosition.xCord, newPosition.yCord));
        return new Slon(newPosition);
    }

    /**
     * Метод получения клеток на пути движения фигуры.
     * @param destination клетка финального положения
     * @return список клеток на пути
     * @throws ImpossibleMoveException выброс в случае, если неверно заданы координаты перемещения
     */
    public Cell[] way(Cell destination) throws ImpossibleMoveException {

        Cell[] cellOnTheWay = null;

        //Проверка правильности заданных координат
        boolean xValid = 1 <= destination.xCord && destination.xCord <= 8;
        boolean yValid = 1 <= destination.yCord && destination.yCord <= 8;
        //Проверка, что фигура может быть передвинута на указанную ячейку
        boolean wayValid = Math.abs(position.xCord - destination.xCord) == Math.abs(position.yCord - destination.yCord);

        if (xValid && yValid && wayValid) {
            int amountOfSteps = Math.abs(position.xCord - destination.xCord);
            cellOnTheWay = new Cell[amountOfSteps];
            int x = position.xCord;
            int y = position.yCord;
            for (int i = 0; i < cellOnTheWay.length; i++) {
                if (destination.xCord > position.xCord) {
                    x++;
                } else {
                    x--;
                }
                if (destination.yCord > position.yCord) {
                    y++;
                } else {
                    y--;
                }
                cellOnTheWay[i] = new Cell(x, y);
            }
        } else {
            throw new ImpossibleMoveException("Impossible move!");
        }
        return cellOnTheWay;
    }
}