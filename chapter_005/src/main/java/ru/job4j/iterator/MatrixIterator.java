package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int index = 0;
    private int outer = 0;
    private int inner = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    public boolean hasNext() {
        int items = 0;
        for (int[] item : values) {
            items += item.length;
        }
        return items > index;
    }

    public Object next() throws NoSuchElementException {
        if (outer > values.length - 1) {
            throw new NoSuchElementException();
        } else {
            int item = values[outer][inner];
            inner++;
            if (inner == values[outer].length) {
                inner = 0;
                outer++;
            }
            index++;
            return item;
        }
    }
}
