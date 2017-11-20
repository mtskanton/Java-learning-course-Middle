package ru.job4j.map.collisions;

import org.junit.Test;

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
        assertThat(shm.insert("1", 111), is(true));
        assertThat(shm.get("1"), is(111));
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
    public void whenCauseCollisionsThenStillWorks() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.capacity = 2; //малая емкость для создания коллизий
        assertThat(shm.insert("1", 1), is(true));
        assertThat(shm.insert("2", 2), is(true));
        assertThat(shm.insert("3", 3), is(true));
        assertThat(shm.insert("3", 333), is(true));
        assertThat(shm.insert("4", 4), is(true));
        assertThat(shm.insert("5", 5), is(true));
        assertThat(shm.get("3"), is(333));
        assertThat(shm.delete("1"), is(true));
        Integer o = null;
        assertThat(shm.get("1"), is(o));
    }
}