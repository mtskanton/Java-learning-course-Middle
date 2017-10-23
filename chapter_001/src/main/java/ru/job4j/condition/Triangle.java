package ru.job4j.condition;

/**
 * Вычисление площади треугольника.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Triangle {
    /**
     * Объявление переменных класса Point.
     */
    private Point a, b, c;

    /**
     * Конструктор инициализации значений.
     * @param a точка а
     * @param b точка b
     * @param c точка c
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Вычисление расстояние между точками left и right.
     * √(xb - xa)^2 + (yb - ya)^2
     * @param left Координаты точки слева
     * @param right Координаты точки справа
     * @return расстояние между точками left и right
     */
    public double distance(Point left, Point right) {
        double leftX = left.getX();
        double leftY = left.getY();
        double rightX = right.getX();
        double rightY = right.getY();
        return Math.sqrt(Math.pow((rightX - leftX), 2) + Math.pow((rightY - leftY), 2));
    }

    /**
     * Вычисление периметра по длинам сторон.
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Перимент.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Вычисление прощади треуголька по формуле Герона.
     * @return Вернуть прощадь, если треугольник существует или -1
     */
    public double area() {
        double rsl = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            return Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Проверка, можно ли построить треугольник с такими длинами сторон.
     * @param ab Длина от точки a b
     * @param ac Длина от точки a c
     * @param bc Длина от точки b c
     * @return true если сумма любых двух сторон больше третьей стороны
     */
    private boolean exist(double ab, double ac, double bc) {
        return (((ab + bc) > ac) && ((bc + ac) > ab) && (ab + ac) > bc);
    }
}