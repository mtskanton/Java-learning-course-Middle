package ru.job4j.applications;

import java.util.Scanner;
/**
 * Класс взаимодействия с пользователем через консоль.
 */
public class ConsoleInput {
    Scanner scanner = new Scanner(System.in);

    public void say(String statement) {
        System.out.println(statement);
    }

    public String showMenu() {
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
}