package ru.job4j.applications;

/**
 * Точка входа в программу. Обеспечивает работу всего приложения.
 */
public class StartUI {

    private Input input;
    private Tracker tracker;
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";

    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {

    }

    public void init() {

        boolean exit = false;
        while (!exit) {

            String userChoice = input.showMenu();

            switch (userChoice) {
                case ADD:
                    this.addItem();
                    break;
                case SHOW_ALL:
                    this.showAllItems();
                    break;
                case EDIT:
                    this.updateItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FIND_BY_ID:
                    this.showItemById();
                    break;
                case FIND_BY_NAME:
                    this.showAllFoundByName();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Добавить item.
     */
    public void addItem() {
        String name = input.ask("Please enter name");
        String description = input.ask("Please enter description");
        String comment = input.ask("Please enter comment");
        Item item = new Item(name, description, comment);
        Item addedItem = tracker.add(item);
        System.out.println("Item has been added. id: " + addedItem.getId()
                + " name: " + addedItem.getName()
                + " description: " + addedItem.getDescription()
                + " comment: " + addedItem.getComments()[0]);
    }

    /**
     * Показать все записи.
     */
    public void showAllItems() {
        Item[] itemList = tracker.findAll();
        for (Item item : itemList) {
            System.out.println("Item name: " + item.getName()
                    + " description: " + item.getDescription()
                    + " comment: " + item.getComments()[0]);
        }
    }

    /**
     * Обновить карточку.
     */
    public void updateItem() {
        String idItemToChange = input.ask("please insert id for updating");
        Item currentItem = tracker.findById(idItemToChange);
        Item updatedItem = null;
        if (currentItem != null) {
            String name = input.ask("Please enter new name. Old version: " + currentItem.getName());
            String description = input.ask("Please enter new description. Old version: " + currentItem.getDescription());

            Item changedItem = new Item(name, description, "");
            changedItem.setId(currentItem.getId());
            tracker.update(changedItem);

            updatedItem = tracker.findById(changedItem.getId());
            System.out.println("Item has been changed. id: " + updatedItem.getId()
                    + " name: " + updatedItem.getName()
                    + " description: " + updatedItem.getDescription()
                    + " comment: " + updatedItem.getComments()[0]);
        } else {
            System.out.println("id is incorrect");
        }
    }

    /**
     * Удалить карточку.
     */
    public void deleteItem() {
        String idItemToDelete = input.ask("please insert id for deleting");
        tracker.delete(idItemToDelete);
        System.out.println("If id was correct, Item has been deleted");
    }

    /**
     * Показать по запросу id.
     */
    public void showItemById() {
        String idItemToFind = input.ask("please insert id of requested item");
        Item foundItem = tracker.findById(idItemToFind);
        if (foundItem != null) {
            System.out.print("name: " + foundItem.getName()
                    + " description: " + foundItem.getDescription()
                    + " comment: " + foundItem.getComments()[0]);
        } else {
            System.out.println("Item has not been found! Please check id.");
        }
    }

    /**
     * Показать все по запросу имени.
     */
    public void showAllFoundByName() {
        String nameItemToFind = input.ask("please insert name for searching");
        Item[] itemList = tracker.findByName(nameItemToFind);
        //проверяем, что как минимум один элемент есть
        if (itemList.length > 0) {
            for (Item item : itemList) {
                System.out.println("Item name: " + item.getName()
                        + " description: " + item.getDescription()
                        + " comment: " + item.getComments()[0]);
            }
        } else {
            System.out.println("No item found matched: " + nameItemToFind);
        }
    }
}
