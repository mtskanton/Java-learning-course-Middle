package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на обратный порядок элементов массива.
 */
public class TurnTest {
    /**
     * Обратный порядок входного массива с числами от одного до пяти.
     */
    @Test
    public void whenDirectArrayFromOneToFiveThenReverseOrder() {
        Turn t = new Turn();
        int[] originArray = {1, 2, 3, 4, 5};
        int[] result = t.back(originArray);
        int[] expected = {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Обратный порядок входного массива с последовательностью чисел 4, 1, 6, 2.
     */
    @Test
    public void whenDirectArrayFourOneSixTwoThenReverseOrder() {
        Turn t = new Turn();
        int[] originArray = {4, 1, 6, 2};
        int[] result = t.back(originArray);
        int[] expected = {2, 6, 1, 4};
        assertThat(result, is(expected));
    }

}