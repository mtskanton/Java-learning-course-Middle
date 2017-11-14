package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {
    private Iterator<Integer> iterator = null;
    private Integer integer = null;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        iterator = it.next();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                integer = iterator.next();
                //если есть еще итераторы, но в текущем закончились значения, то выбираем следующий итератор
                if (it.hasNext()) {
                    if (!this.hasNext()) {
                        iterator = it.next();
                    }
                }
                return integer;
            }
        };
    }
}
