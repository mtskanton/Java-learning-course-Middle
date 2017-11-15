package ru.job4j.generics;

public class SimpleArray<T> {

    private Object[] array;
    private int index = 0;

    SimpleArray(int size) {
        array = new Object[size];
    }

    public void add(T value) {
        this.array[index++] = value;
    }

    public void update(int position, T value) {
        this.array[position] = value;
    }

    public void delete(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[array.length - 1] = null;
    }

    public T get(int position) {
        return (T) this.array[position];
    }

}
