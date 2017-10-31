package ru.job4j.professions;

/**
 * Класс ученика. Использует Teacher.
 */
public class Pupil {
    /**
     * Имя ученика.
     */
    private String name;
    /**
     * Номер класса ученика.
     */
    private int classNumber;

    /**
     * Конструктор. Инициализация значений.
     * @param name имя ученика
     * @param classNumber номер класса
     */
    public Pupil(String name, int classNumber) {
        this.name = name;
        this.classNumber = classNumber;
    }

    /**
     * Узнать имя ученика.
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    public int getClassNumber() {
        return this.classNumber;
    }

}
