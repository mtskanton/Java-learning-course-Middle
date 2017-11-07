package ru.job4j.applications;

import java.util.*;
/**
 * Класс-обертка по работе с массивом заявок.
 */
public class Tracker {
    /**
     * Список хранимых заявок класса Item.
     */
    private ArrayList<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    /**
     * Добавление заявки.
     * @param item заявка
     * @return новая заявка
     */
    public Item add(Item item) {
        String generatedId = String.valueOf(System.currentTimeMillis() + RN.nextInt());
        item.setId(generatedId);
        this.items.add(item);
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
        for (Item i : this.items) {
            if (i.getId().equals(id)) {
                this.items.remove(0);
                break;
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
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> foundByName = new ArrayList<>();
        for (Item i : this.items) {
            if (i.getName().contains(key)) {
                foundByName.add(i);
            }
        }
        return foundByName;
    }

    /**
     * Поиск всех заявок.
     * @return все заявки
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }
}
