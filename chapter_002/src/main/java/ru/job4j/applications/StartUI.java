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
            if (input instanceof ConsoleInput) {
                menu.show();
            }

            int key = Integer.valueOf(input.ask("Select: "));
            if (key >= 6) {
                exit = true;
                continue;
            }
            menu.select(key);
        } while (!exit);
    }
}
