package ru.job4j.sorting;

import java.util.*;

/**
 * Реализована сортировка ArrayList по имени в обратном порядке и далее по id
 */
public class ArrayListSorting {
    public static void main(String[] args) {

        ArrayList<UserArrayListSorting> arrList = new ArrayList<>();
        arrList.add(new UserArrayListSorting(2, "name2"));
        arrList.add(new UserArrayListSorting(3, "name1"));
        arrList.add(new UserArrayListSorting(1, "name1"));

        Collections.sort(arrList, UserArrayListSorting.backSort.thenComparing(UserArrayListSorting.idSort));

        System.out.println(arrList);
    }
}
