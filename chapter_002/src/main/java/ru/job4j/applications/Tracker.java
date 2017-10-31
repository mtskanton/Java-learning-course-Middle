package ru.job4j.applications;

import java.util.*;
/**
 * Класс-обертка по работе с массивом заявок.
 */
public class Tracker {
    /**
     * Список хранимых заявок класса Item.
     */
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Добавление заявки.
     * @param item заявка
     * @return новая заявка
     */
    public Item add(Item item) {
        String generatedId = String.valueOf(System.currentTimeMillis() + RN.nextInt());
        item.setId(generatedId);
        this.items[position++] = item;
        return item;
    }

    /**
     * Редактирование заявки.
     * @param updatedItem отредактированная заявка
     */
    public void update(Item updatedItem) {
        Item item = findById(updatedItem.getId());
        if (item != null) {
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
        }
    }

    /**
     * Удаление заявки.
     * удаляем используя System.arraycopy
     * @param id номер заявки на удаление
     */
    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, 100 - i - 1);
                position--;
            }
        }
    }

    /**
     * Поиск заявки по id.
     * @param id заявки для поиска
     * @return заявку
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Поиск заявки по имени.
     * @param key параментр для поиска
     * @return список заявок с совпадением
     */
    public Item[] findByName(String key) {
        Item[] newArray;
        Item[] arr = new Item[0];
        int coincidence = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().contains(key)) {
                //Если найден по имени Item создаем массив из 1 элемента.
                //Если еще найден элемент то создаем массив из 2 элементов копируем элементы из старого массива
                // и добавляем найденный.
                newArray = new Item[++coincidence];
                for (int c = 0; c < coincidence - 1; c++) {
                    newArray[c] = arr[c];
                }
                newArray[coincidence - 1] = items[i];
                arr = newArray;
            }
        }
        return arr;
    }

    /**
     * Поиск всех заявок.
     * @return все заявки
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i < this.position; i++) {
            result[i] = this.items[i];
        }
        return result;
    }
}
