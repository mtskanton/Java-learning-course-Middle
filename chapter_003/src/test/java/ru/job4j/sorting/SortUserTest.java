package ru.job4j.sorting;

import java.util.*;
import org.junit.Test;
import ru.job4j.OutputTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortUserTest extends OutputTest {
    /**
     * Test of sorting with using of Comparable
     */
    @Test
    public void whenSortUsersByAgeThenListOfAgeByIncreasing() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 30),
                new User("Amber", 20),
                new User("Bill", 10))
        );

        Set<User> sortedList = su.sort(list);
        System.out.print(sortedList);
        String expected = "[User{name='Bill', age=10}, User{name='Amber', age=20}, User{name='Zig', age=30}]";
        assertThat(expected, is(output.toString()));
    }

    /**
     * Test of sorting with using of Comparable. Users with same age to be compared by names
     */
    @Test
    public void whenSortUsersWithSameAgeThenTheyAreComparedByName() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 20),
                new User("Amber", 20),
                new User("Amber", 30),
                new User("Bill", 20),
                new User("Bill", 10))
        );

        Set<User> sortedList = su.sort(list);
        System.out.print(sortedList);
        String expected = "[User{name='Bill', age=10}, "
                        + "User{name='Amber', age=20}, "
                        + "User{name='Bill', age=20}, "
                        + "User{name='Zig', age=20}, "
                        + "User{name='Amber', age=30}]";
        assertThat(expected, is(output.toString()));
    }

    /**
     * Test of sorting by name length with using of Comparator
     */
    @Test
    public void whenSortByNameLengthThenSortedList() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 30),
                new User("Amber", 20),
                new User("Bill", 10))
        );

        List<User> sortedList = su.sortNameLength(list);
        System.out.print(sortedList);
        String expected = "[User{name='Zig', age=30}, User{name='Bill', age=10}, User{name='Amber', age=20}]";
        assertThat(expected, is(output.toString()));
    }

    /**
     * Test of sorting by name and age with using of Comparator
     */
    @Test
    public void whenSortByNameAndAgeThenSortedList() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 30),
                new User("Amber", 20),
                new User("Bill", 20),
                new User("Bill", 10)
        ));

        List<User> sortedList = su.sortByAllFields(list);
        System.out.print(sortedList);
        String expected = "[User{name='Amber', age=20}, User{name='Bill', age=10}, "
                            + "User{name='Bill', age=20}, User{name='Zig', age=30}]";
        assertThat(expected, is(output.toString()));
    }
}
