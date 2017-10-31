package ru.job4j.applications;

import java.util.Scanner;
/**
 * Класс взаимодействия с пользователем через консоль.
 */
public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);

    public void say(String statement) {
        System.out.println(statement);
    }

    public String showMenu() {
        //запуск меню. Выводится пока не выбран EXIT
        System.out.println("\r\nPlease choose task's name");
        System.out.println("---------MENU-----------");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        return this.getAnswer();
    }

    public String getAnswer() {
        return scanner.next();
    }

    /**
     * Добавить item.
     */
    public void addItem(Tracker tracker) {
        this.say("Please enter name");
        String name = this.getAnswer();
        this.say("Please enter description");
        String description = this.getAnswer();
        this.say("Please enter comment");
        String comment = this.getAnswer();
        Item item = new Item(name, description, comment);

        Item addedItem = tracker.add(item);
        this.say("Item has been added:");
        this.say(" id: " + addedItem.getId()
                + " name: " + addedItem.getName()
                + " description: " + addedItem.getDescription()
                + " comment: " + addedItem.getComments()[0]);
    }

    /**
     * Показать все записи.
     */
    public void showAllItems(Tracker tracker) {
        Item[] itemList = tracker.findAll();
        for (Item item : itemList) {
            this.say("Item id: " + item.getId()
                    + " name: " + item.getName()
                    + " description: " + item.getDescription()
                    + " comment: " + item.getComments()[0]);
        }
    }

    /**
     * Обновить карточку.
     */
    public void updateItem(Tracker tracker) {
        this.say("please insert id for updating");
        String idItemToChange = this.getAnswer();
        Item currentItem = tracker.findById(idItemToChange);
        Item updatedItem = null;
        if (currentItem != null) {
            this.say("Please enter new name. Old version: " + currentItem.getName());
            String name = this.getAnswer();
            this.say("Please enter new description. Old version: " + currentItem.getDescription());
            String description = this.getAnswer();

            Item changedItem = new Item(name, description, "");
            changedItem.setId(currentItem.getId());
            tracker.update(changedItem);

            updatedItem = tracker.findById(changedItem.getId());
            this.say("Item has been changed:");
            this.say("id: " + updatedItem.getId()
                    + " name: " + updatedItem.getName()
                    + " description: " + updatedItem.getDescription()
                    + " comment: " + updatedItem.getComments()[0]);
        } else {
            this.say("id is incorrect");
        }
    }

    /**
     * Удалить карточку.
     */
    public void deleteItem(Tracker tracker) {
        this.say("please insert id for deleting");
        String idItemToDelete = this.getAnswer();
        tracker.delete(idItemToDelete);
        this.say("If id was correct, Item has been deleted");
    }

    /**
     * Показать по запросу id.
     */
    public void showItemById(Tracker tracker) {
        this.say("please insert id of requested item");
        String idItemToFind = this.getAnswer();
        Item foundItem = tracker.findById(idItemToFind);
        if (foundItem != null) {
            this.say("name: " + foundItem.getName()
                    + " description: " + foundItem.getDescription()
                    + " comment: " + foundItem.getComments()[0]);
        } else {
            this.say("Item has not been found! Please check id.");
        }
    }

    /**
     * Показать все по запросу имени.
     */
    public void showAllFoundByName(Tracker tracker) {
        this.say("please insert name for searching");
        String nameItemToFind = this.getAnswer();
        Item[] itemList = tracker.findByName(nameItemToFind);
        //проверяем, что как минимум один элемент есть
        if (itemList.length > 0) {
            for (Item item : itemList) {
                this.say("Item id: " + item.getId()
                        + " name: " + item.getName()
                        + " description: " + item.getDescription()
                        + " comment: " + item.getComments()[0]);
            }
        } else {
            this.say("No item found matched: " + nameItemToFind);
        }
    }
}