package ru.job4j.generics;

public class AbstractStore<T extends Base> implements Store<T> {
    protected int size;
    protected SimpleArray<T> sa;

    AbstractStore(int size) {
        this.size = size;
        this.sa = new SimpleArray<>(size);
    }

    public T add(T model) {
        sa.add(model);
        return model;
    }

    public T update(T model) {
        String id = model.getId();
        sa.update(findById(id), model);
        return model;
    }

    public boolean delete(String id) {
        boolean success = false;
        int index = this.findById(id);
        if (index >= 0) {
            sa.delete(index);
            success = true;
        }
        return success;
    }

    public T get(String id) {
        int index = this.findById(id);
        if (index >= 0) {
            return sa.get(index);
        }
        return null;
    }

    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (sa.get(i) != null && sa.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
