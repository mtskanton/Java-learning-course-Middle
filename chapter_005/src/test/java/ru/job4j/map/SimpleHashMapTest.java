package ru.job4j.map;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    @Test
    public void whenAddItemToMapThenCanGetIt() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        assertThat(shm.insert("1", 1), is(true));
        assertThat(shm.get("1"), is(1));
        Integer o = null;
        assertThat(shm.get("2"), is(o));
    }

    @Test
    public void whenAddItemsWithSameKeysThenNoDuplicates() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        assertThat(shm.insert("1", 1), is(true));
        assertThat(shm.insert("1", 111), is(false));
        assertThat(shm.get("1"), is(1));
    }

    @Test
    public void whenDeleteItemFromMapThenMoreThere() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        assertThat(shm.insert("1", 1), is(true));
        assertThat(shm.get("1"), is(1));
        assertThat(shm.delete("1"), is(true));
        Integer o = null;
        assertThat(shm.get("1"), is(o));
    }

    @Test
    public void whenAddItemThenHasItInSet() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("1", 1);
        Iterator<Integer> iterator = shm.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddItemsThenNoDuplicates() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("1", 1);
        shm.insert("2", 2);
        shm.insert("2", 2);
        shm.insert("3", 3);
        Iterator<Integer> iterator = shm.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
}