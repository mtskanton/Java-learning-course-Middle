package ru.job4j.professions;

/**
 * Класс клиента. Использует инженер.
 */
public class Customer {
    private String name;

    /**
     * Конструктор устанавливает имя клиента.
     * @param name имя
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Узнать имя клиента.
     * @return имя
     */
    public String getName() {
        return this.name;
    }
}
