package ru.job4j.applications;

/**
 * Интерфейс ввода данных.
 */
public interface Input {
    public String showMenu();
    public void addItem(Tracker tracker);
    public void showAllItems(Tracker tracker);
    public void updateItem(Tracker tracker);
    public void deleteItem(Tracker tracker);
    public void showItemById(Tracker tracker);
    public void showAllFoundByName(Tracker tracker);
}
