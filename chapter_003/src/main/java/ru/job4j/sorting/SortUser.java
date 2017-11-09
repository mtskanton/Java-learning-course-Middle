package ru.job4j.sorting;

import java.util.*;

public class SortUser {

    Set<User> sort(List<User> list) {
        Set<User> sortedList = new TreeSet<>();

        sortedList.addAll(list);

        return sortedList;
    }

    public List<User> sortNameLength(List<User> userList) {
        Comparator<User> compareByNameLength = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };

        userList.sort(compareByNameLength);
        return userList;
    }

    public List<User> sortByAllFields(List<User> userList) {
        Comparator<User> compareByName = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Comparator<User> compareByAge = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        userList.sort(compareByName.thenComparing(compareByAge));
        return userList;
    }
}
