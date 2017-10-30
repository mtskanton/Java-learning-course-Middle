package ru.job4j.strategy;
/**
 * Класс, реализующий стратегию.
 */
public class Square implements Shape {
    /**
     * Переопределение метода.
     * @return строку фигуры квадрат
     */
    @Override
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++\n");
        pic.append("++++\n");
        pic.append("++++\n");
        pic.append("++++\n");
        return pic.toString();
    }
}
