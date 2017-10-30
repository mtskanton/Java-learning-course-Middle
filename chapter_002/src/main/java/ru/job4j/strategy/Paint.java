package ru.job4j.strategy;

/**
 * Класс выбора стратегии.
 */
public class Paint {
    /**
     * Рисование фигуры в зависимости от класса, реализующего интерфейс Shape.
     * @param shape класс реализующий интерфейс Shape
     */
    public void draw(Shape shape) {
        String sh = shape.pic();
        System.out.print(sh);
    }
}
