package ru.job4j.applications;

/**
 * Точка входа в программу. Обеспечивает работу всего приложения.
 */
public class StartUI {
    ConsoleInput input = new ConsoleInput();
    Tracker tracker = new Tracker();
    public static final String ADD = "0";
    public static final String SHOW_ALL = "1";
    public static final String EDIT = "2";
    public static final String DELETE = "3";
    public static final String FIND_BY_ID = "4";
    public static final String FIND_BY_NAME = "5";
    public static final String EXIT = "6";

    public static void main(String[] args) {
        new StartUI().init();
    }

    public void init() {

        Tracker tracker = new Tracker();

        boolean exit = false;
        while (!exit) {
            //запуск меню. Выводится пока не выбран EXIT
            input.say("\r\nPlease choose task's name");
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
                default:
                    break;
            }
        }
    }

    /**
     * Добавить item.
     */
    private void addItem() {
        input.say("Please enter name");
        String name = input.getAnswer();
        input.say("Please enter description");
        String description = input.getAnswer();
        input.say("Please enter comment");
        String comment = input.getAnswer();
        Item item = new Item(name, description, comment);

        Item addedItem = tracker.add(item);
        input.say("Item has been added:");
        input.say(" id: " + addedItem.getId()
                + " name: " + addedItem.getName()
                + " description: " + addedItem.getDescription()
                + " comment: " + addedItem.getComments()[0]);
    }

    /**
     * Показать все записи.
     */
    private void showAllItems() {
        Item[] itemList = tracker.findAll();
        for (Item item : itemList) {
            input.say("Item id: " + item.getId()
                    + " name: " + item.getName()
                    + " description: " + item.getDescription()
                    + " comment: " + item.getComments()[0]);
        }
    }

    /**
     * Обновить карточку.
     */
    private void updateItem() {
        input.say("please insert id for updating");
        String idItemToChange = input.getAnswer();
        Item currentItem = tracker.findById(idItemToChange);
        Item updatedItem = null;
        if (currentItem != null) {
            input.say("Please enter new name. Old version: " + currentItem.getName());
            String name = input.getAnswer();
            input.say("Please enter new description. Old version: " + currentItem.getDescription());
            String description = input.getAnswer();

            Item changedItem = new Item(name, description, "");
            changedItem.setId(currentItem.getId());
            tracker.update(changedItem);

            updatedItem = tracker.findById(changedItem.getId());
            input.say("Item has been changed:");
            input.say("id: " + updatedItem.getId()
                    + " name: " + updatedItem.getName()
                    + " description: " + updatedItem.getDescription()
                    + " comment: " + updatedItem.getComments()[0]);
        } else {
            input.say("id is incorrect");
        }
    }

    /**
     * Удалить карточку.
     */
    private void deleteItem() {
        input.say("please insert id for deleting");
        String idItemToDelete = input.getAnswer();
        tracker.delete(idItemToDelete);
        input.say("If id was correct, Item has been deleted");
    }

    /**
     * Показать по запросу id.
     */
    private void showItemById() {
        input.say("please insert id of requested item");
        String idItemToFind = input.getAnswer();
        Item foundItem = tracker.findById(idItemToFind);
        if (foundItem != null) {
            input.say("name: " + foundItem.getName()
                    + " description: " + foundItem.getDescription()
                    + " comment: " + foundItem.getComments()[0]);
        } else {
            input.say("Item has not been found! Please check id.");
        }
    }

    /**
     * Показать все по запросу имени.
     */
    private void showAllFoundByName() {
        input.say("please insert name for searching");
        String nameItemToFind = input.getAnswer();
        Item[] itemList = tracker.findByName(nameItemToFind);
        //проверяем, что как минимум один элемент есть
        if (itemList[0] != null) {
             for (Item item : itemList) {
                 if (item != null) {
                     input.say("Item id: " + item.getId()
                             + " name: " + item.getName()
                             + " description: " + item.getDescription()
                             + " comment: " + item.getComments()[0]);
                 }
            }
        } else {
            input.say("No item found matched: " + nameItemToFind);
        }
    }
}
