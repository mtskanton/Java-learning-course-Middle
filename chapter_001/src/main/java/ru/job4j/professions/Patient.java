package ru.job4j.professions;

/**
 * Класс пациент. Используется в классе Doctor.
 */
public class Patient {
    /**
     * Имя пациента.
     */
    private String name;

    /**
     * Конструктор с инициализацией имени.
     * @param name имя пациента
     */
    public Patient(String name) {
        this.name = name;
    }

    /**
     * Узнать имя пациента.
     * @return имя
     */
    public String getName() {
        return this.name;
    }
}
