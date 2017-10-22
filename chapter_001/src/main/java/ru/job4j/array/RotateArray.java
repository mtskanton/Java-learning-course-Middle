package ru.job4j.array;

/**
 * Программа поворота квадратного массива.
 * @author  Anton Matsik
 * @since  22.10.2017
 */
public class RotateArray {
    /**
     * Принимает двумерный массив, поворачивает на 90, возвращает массив.
     * @param array исходный двумерный массив
     * @return повернутый двумерный массив
     */
    public int[][] rotate(int[][] array) {
        //размер матрицы. Одно значение, т.к. симметричная.
        int size = array.length;
        int[][] rotatedArray = new int[size][size];

        //перебираем ряды матрицы
        for (int r = 0; r < size; r++) {
            //перебираем колонки матрицы
            for (int c = 0; c < size; c++) {
                rotatedArray[r][c] = array[size - c - 1][r];
            }
        }
        return rotatedArray;
    }
}
