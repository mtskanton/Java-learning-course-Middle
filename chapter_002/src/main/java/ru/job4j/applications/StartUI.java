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
                    input.addItem(tracker);
                    break;
                case SHOW_ALL:
                    input.showAllItems(tracker);
                    break;
                case EDIT:
                    input.updateItem(tracker);
                    break;
                case DELETE:
                    input.deleteItem(tracker);
                    break;
                case FIND_BY_ID:
                    input.showItemById(tracker);
                    break;
                case FIND_BY_NAME:
                    input.showAllFoundByName(tracker);
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }
}
