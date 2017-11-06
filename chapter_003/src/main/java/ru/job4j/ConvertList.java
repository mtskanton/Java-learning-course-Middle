package ru.job4j;

import java.util.*;

public class ConvertList {
    /**
     * Convert arr[][] to ArrayList.
     * @param array integer array [][]
     * @return ArrayList
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Convert ArrayList to array[][].
     * @param list ArrayList
     * @param rows amount of rows required
     * @return array[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        //add 0 if required
        int numOfNulls = rows - list.size() % rows;
        numOfNulls = (rows - list.size() % rows) == rows ? 0 : numOfNulls;
        for (int i = 0; i < numOfNulls; i++) {
            list.add(0);
        }

        Iterator<Integer> iterator = list.iterator();

        int columns = list.size() / rows;
        int[][] array = new int[rows][columns];

        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    array[i][j] = iterator.next();
                }
            }
        }
        return array;
    }
}
