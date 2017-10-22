package ru.job4j.loop;

/**
 * Риосвание шахматной доски в псевдографике.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Board {
    /**
     * Рисование доски произвольных размеров.
     * @param width ширина
     * @param height высота
     * @return строка для выведения в псевдографике
     */
    public String paint(int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                if (isOdd(y)) {
                    if (isOdd(x)) {
                        sb.append("x");
                    } else {
                        sb.append(" ");
                    }
                } else {
                    if (isOdd(x)) {
                        sb.append(" ");
                    } else {
                        sb.append("x");
                    }
                }
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    /**
     * Рисование доски шириной в нечетную величину.
     * @param width ширина
     * @param height высота
     * @return строка для выведения в псевдографике
     */
    public String paintOddWidthBoard(int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int cell = 1; cell <= (width * height); cell++) {
            if (isOdd(cell)) {
                sb.append("x");
            } else {
                sb.append(" ");
            }
            if (cell % width == 0) {
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    /**
     * Проверка на нечетность числа.
     * @param number число для проверки
     * @return true если число нечетное
     */
    public boolean isOdd(int number) {
        return (number % 2 != 0);
    }
}