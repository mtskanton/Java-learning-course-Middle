package ru.job4j.professions;

/**
 * Класс проекта. Используется в классе Engineer.
 */
public class Project {
    private String typeOfWork;
    private int projectNumber;
    private Customer customer;

    /**
     * Создание проекта.
     * @param num номер проекта
     * @param client клиент
     * @param work наименование работ
     */
    public Project(int num, Customer client, String work) {
        this.typeOfWork = work;
        this.projectNumber = num;
        this.customer = client;
    }

    /**
     * Информация об актуальном проекте.
     * @return описание проекта
     */
    public String getProjectInfo() {
        return "проект № " + projectNumber + ": " + typeOfWork + " для " + customer.getName() + "";
    }
}
