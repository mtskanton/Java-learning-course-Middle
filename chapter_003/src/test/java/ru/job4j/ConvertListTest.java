package ru.job4j;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class ConvertListTest extends OutputTest {
    /**
     * Convert the array into the list.
     */
    @Test
    public void whenConvertArrayToListThenList() {
        ConvertList convert = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7}};
        List<Integer> list = convert.toList(array);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        String expected = "1234567";
        assertThat(expected, is(output.toString()));
    }

    /**
     * Convert the list into the array with required amount of rows.
     */
    @Test
    public void whenConvertListToArrayOfCertainRowsThenArrayWithRows() {
        ConvertList convert = new ConvertList();
        //fill the list
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7;) {
            list.add(++i);
        }

        Integer[][] arr = convert.toArray(list, 3);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
        String expected = "123\r\n456\r\n700\r\n";
        assertThat(expected, is(output.toString()));
    }

    /**
     * Convert arrays List into Integer list.
     */
    @Test
    public void whenArraysListWithDifferentAmountOfItemsThenOneIntegerList() {
        ConvertList cl = new ConvertList();
        
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> arrList = cl.convert(list);
        System.out.print(arrList);

        String expected = "[1, 2, 3, 4, 5, 6]";
        assertThat(expected, is(output.toString()));
    }
}
