package ru.job4j.condition;

/**
 * Положение точки на заданной кривой.
 * @author Anton Matsik
 * @since 21.01.2017
 */
public class Point {
    /**
     * Заданные точки.
     */
    private int x, y;

    /**
     * Конструктор с инициализацией значений.
     * @param x координата х
     * @param y координата у
     */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * возвращает значение Х.
     * @return х
     */
    public int getX() {
        return x;
    }

    /**
     * возвращает значение У.
     * @return у
     */
    public int getY() {
        return y;
    }

    /**
     * Метод, проверяющий, находится ли точка на кривой.
     * @param a переменная а
     * @param b переменная b
     * @return true если находится
     */
    public boolean is(int a, int b) {
        return (y == (a * x + b));
    }
}
