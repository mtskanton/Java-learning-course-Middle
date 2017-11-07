package ru.job4j;

import com.sun.org.apache.xpath.internal.SourceTree;

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
    public Integer[][] toArray(List<Integer> list, int rows) {
        //add 0 if required
        int numOfNulls = rows - list.size() % rows;
        numOfNulls = (rows - list.size() % rows) == rows ? 0 : numOfNulls;
        for (int i = 0; i < numOfNulls; i++) {
            list.add(0);
        }

        Iterator<Integer> iterator = list.iterator();

        int columns = list.size() / rows;
        Integer[][] array = new Integer[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = iterator.next();
            }
        }
        return array;
    }

    /**
     * Convert arrays List into Integer list.
     * @param list arrays list
     * @return Integer list
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> convertedList = new ArrayList<>();

        for (int[] l : list) {
            for (int i = 0; i < l.length; i++) {
                convertedList.add(l[i]);
            }
        }
        return convertedList;
    }
}
