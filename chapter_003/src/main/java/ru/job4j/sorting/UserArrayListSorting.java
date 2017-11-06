package ru.job4j.sorting;

import java.util.Comparator;

public class UserArrayListSorting implements Comparable<UserArrayListSorting> {
    int id;
    String name;

    UserArrayListSorting(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    /**
     * Если используется сортировка Collections.sort(arrList);
     * @param us класс User
     * @return
     */
    @Override
    public int compareTo(UserArrayListSorting us) {
        return this.name.compareTo(us.getName());
    }

    /**
     * Если используется сортировка Collections.sort(arrList, UserArrayListSorting.backSort);
     */
    public static Comparator<UserArrayListSorting> backSort = new Comparator<UserArrayListSorting>() {
        @Override
        public int compare(UserArrayListSorting o1, UserArrayListSorting o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };

    /**
     * Если используется сортировка Collections.sort(arrList, UserArrayListSorting.backSort.thenComparing(UserArrayListSorting.idSort));
     */
    public static Comparator<UserArrayListSorting> idSort = new Comparator<UserArrayListSorting>() {
        @Override
        public int compare(UserArrayListSorting o1, UserArrayListSorting o2) {
            return o2.getId() - o1.getId();
        }
    };

    @Override
    public String toString() {
        return "UserArrayListSorting{"
                + "id=" + id
                + ", name='" + name
                + '\'' +  '}';
    }
}
