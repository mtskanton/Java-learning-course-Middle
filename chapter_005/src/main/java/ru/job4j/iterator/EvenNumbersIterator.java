package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    private final int[] array;
    int index = 0;

    EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    public boolean hasNext() {
        return  evenExist();
    }

    public Object next() {
        if (!evenExist()) {
            throw new NoSuchElementException();
        } else {
            int result = 0;
            for (int i = index; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    result = array[i];
                    index = i;
                    break;
                }
            }
            index++;
            return result;
        }
    }

    private boolean evenExist() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }
}
