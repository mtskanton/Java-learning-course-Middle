package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {

    private int[] values;
    private int index = 0;

    PrimeIterator(int[] values) {
        this.values = values;
    }

    public boolean hasNext() {
        boolean prime = false;

        for (int i = index; i < values.length; i++) {
            if (!isComposite(i)) {
                prime = true;
                break;
            }
        }

        return prime;
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {

            for (int i = index; i < values.length; i++) {
                if (!isComposite(i)) {
                    index = i;
                    break;
                }
            }

            return values[index++];
        }
    }

    private  boolean isComposite(int i) {
        boolean composite = false;

        //единица не натуральное число, принимаем как составное
        if (values[i] == 1) {
            composite = true;
        }

        //проверяем число на деление без остатка
        for (int j = 2; j < values[i]; j++) {
            if (values[i] % j == 0) {
                composite = true;
                break;
            }
        }

        return composite;
    }
}
