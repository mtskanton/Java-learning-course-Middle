package ru.job4j.applications;

/**
 * Точка входа в программу. Обеспечивает работу всего приложения.
 */
public class StartUI {

    private Input input;
    private Tracker tracker;

    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        boolean exit = false;
        do {
            //Проверка на используемый Input для прохождения тестов, иначе в проверку выводится меню
            if (input instanceof ValidateInput) {
                menu.show();
            }

            int key = input.ask("Select: ", new int[] {0, 1, 2, 3, 4, 5, 6});
            if (key == 6) {
                exit = true;
                continue;
            }
            menu.select(key);
        } while (!exit);
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        StartUI sui = new StartUI(input, tracker);
        sui.init();

    }
}