package ru.job4j.loop;

/**
 * Рисование пирамиды в псевдографике.
 * @author Anton Matsik
 * @since 22.10.2017
 */
public class Paint {
    /**
     * рисование пирамиды по заданной высоте.
     * @param height высота пирамиды
     * @return строковое представление пирамиды
     */
    public String pyramid(int height) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < height; row++) {
            //рисование пропусков слева
            for (int i = 0; i < (height - row - 1); i++) {
                sb.append(" ");
            }
            //рисование тела пирамиды
            for (int i = 0; i < (row * 2 + 1); i++) {
                sb.append("^");
            }
            //рисование пропусков справа
            for (int i = 0; i < (height - row - 1); i++) {
                sb.append(" ");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
}
