package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            for (int i = index; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    index = i;
                    break;
                }
            }
            return array[index++];
        }
    }
}
