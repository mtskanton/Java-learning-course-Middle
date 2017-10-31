package ru.job4j.professions;

/**
 * Класс профессии Инженер.
 */
public class Engineer extends Profession {
    /**
     * Инициализация имени и зарплаты.
     * @param name
     * @param salary
     */
    Engineer(String name, double salary) {
        super(name, salary);
    }
    /**
     * Получение проекта в работу.
     * @param client клиент
     * @param work тип работ
     * @return проект
     */
    public Project takeProject(Customer client, String work) {
        int number = (int) (Math.random() * 100);
        return new Project(number, client, work);
    }

    /**
     * Выполнение работ по проекту.
     * @param project проект
     */
    public void workOnProject(Project project) {
        System.out.println("Инженер " + this.getName() + " курирует " + project.getProjectInfo());
    }
}
