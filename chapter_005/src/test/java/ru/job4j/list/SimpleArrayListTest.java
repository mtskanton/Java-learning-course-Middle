package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {
    @Test
    public void whenAddItemThenCanGetItFromTheList() {
        SimpleArrayList<Integer> sal = new SimpleArrayList<>();
        sal.add(1);
        sal.add(2);
        sal.add(3);
        int result = sal.get(0);
        assertThat(result, is(1));
    }

    @Test
    public void whenAddItemsThenCanIterateThem() {
        SimpleArrayList<Integer> sal = new SimpleArrayList<>();
        sal.add(1);
        sal.add(2);
        sal.add(3);
        Iterator<Integer> iterator = sal.iterator();
        int one = iterator.next();
        assertThat(one, is(1));
        int two = iterator.next();
        assertThat(two, is(2));
        int three = iterator.next();
        assertThat(three, is(3));
        boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }
}