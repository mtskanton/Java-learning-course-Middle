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
     * удаляем перемещением всех элементов по индексу на позицию -1 начиная от удаленного
     * @param id номер заявки на удаление
     */
    public void delete(String id) {
        //находим индекс удаленной заявки
        int delIndex = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                delIndex = i;
                position--;
            }
        }
        //проверка на случай удаления последнего элемента массива
        for (int j = delIndex; j < this.position; j++) {
            if (j != items.length - 1) {
                this.items[j] = this.items[j + 1];
            } else {
                this.items[j] = null;
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
        Item[] result = new Item[items.length];
        int i = 0;
        for (Item item : items) {
            if (item != null && item.getName().contains(key)) {
                result[i] = item;
                i++;
            }
        }
        return result;
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
