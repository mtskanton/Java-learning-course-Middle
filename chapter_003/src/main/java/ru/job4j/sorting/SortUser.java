package ru.job4j.sorting;

import java.util.*;

public class SortUser {

    Set<User> sort(List<User> list) {
        Set<User> sortedList = new TreeSet<>();

        sortedList.addAll(list);

        return sortedList;
    }

    public static void main(String[] args) {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 30),
                new User("Amber", 20),
                new User("Bill", 10))
        );

        Set<User> sortedList = su.sort(list);
        System.out.print(sortedList);
    }
}
