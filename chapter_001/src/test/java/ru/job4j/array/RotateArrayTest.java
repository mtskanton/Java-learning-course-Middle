package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование поворота квадратного массива.
 */
public class RotateArrayTest {
    /**
     * Тест поворота двумерного массива 2x2.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray rz = new RotateArray();
        int[][] originArray = {{1, 2}, {3, 4}};
        int[][] resultArray = rz.rotate(originArray);
        int[][] expectArray = {{3, 1}, {4, 2}};
        assertThat(resultArray, is(expectArray));
    }

    /**
     * Тест поворота вумерного массива 3x3.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray rz = new RotateArray();
        int[][] originArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] resultArray = rz.rotate(originArray);
        int[][] expectArray = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(resultArray, is(expectArray));
    }
}
