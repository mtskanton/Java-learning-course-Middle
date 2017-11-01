package ru.job4j.applications;

import java.util.Scanner;
/**
 * Класс взаимодействия с пользователем через консоль.
 */
public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);

    /**
     * Запрос данных от пользователя.
     * @param question вопрос
     * @return считанный из консоли ответ
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.next();
    }

    /**
     * Отображение меню для пользователя.
     * @return выбор пользователя
     */
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
        return scanner.next();
    }
}