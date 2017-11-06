package ru.job4j;

/**
 * Класс проверки быстродействия Коллекций по сравнению времени вставки и удаления элементов
 * @author Anton Matsik
 * @since 06.11.2017
 */

import java.util.*;

public class CollectionsCapacity {

    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add("string example" + i);
        }
        return System.currentTimeMillis() - startTime;
    }

    public long delete(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.remove("string example" + i);
        }
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        int amount = 300000;
        ArrayList<String> arr = new ArrayList<>();
        LinkedList<String> linkL = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        CollectionsCapacity collection = new CollectionsCapacity();

        System.out.println(String.format("Testing of Collection for adding and deleting of %s items", amount));

        System.out.println("-----------------------------------");

        long resultAddArrayList = collection.add(arr, amount);
        System.out.print(String.format("ArrayList add: %s мс;   ", resultAddArrayList));

        long resultAddLinkedList = collection.add(linkL, amount);
        System.out.print(String.format("LinkedList add: %s мс;   ", resultAddLinkedList));

        long resultAddTreeSet = collection.add(treeSet, amount);
        System.out.println(String.format("TreeSet add: %s мс;   ", resultAddTreeSet));

        System.out.println("-----------------------------------");

        long resultDeleteArrayList = collection.delete(arr, amount);
        System.out.print(String.format("ArrayList delete: %s мс;   ",  resultDeleteArrayList));

        long resultDeleteLinkedList = collection.delete(linkL, amount);
        System.out.print(String.format("LinkedList delete: %s мс;   ", resultDeleteLinkedList));

        long resultDeleteTreeSet = collection.delete(treeSet, amount);
        System.out.print(String.format("TreeSet delete: %s мс;   ", resultDeleteTreeSet));
    }

}
