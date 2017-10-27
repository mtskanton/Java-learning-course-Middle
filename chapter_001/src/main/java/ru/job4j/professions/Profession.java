package ru.job4j.professions;

/**
 * Класс профессии.
 * @author Anton Matsik
 * @since 27.10.2017
 */
public class Profession {
    /**
     * Имя работника.
     */
    private String name;
    /**
     * Зарплата работника.
     */
    private double salary;

    /**
     * Конструктор инициализации значений.
     * @param name имя
     * @param salary зарплата
     */
    public Profession(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Узнать имя работника.
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Узнать зарплату работника.
     */
    public void getSalary() {
        System.out.println(this.name + " зарабатывает " + salary);
    }
}
