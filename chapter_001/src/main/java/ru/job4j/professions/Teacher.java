package ru.job4j.professions;

/**
 * класс учителя.
 */
public class Teacher extends Profession {
    /**
     * Список учеников.
     */
    private Pupil[] pupilList;

    /**
     * Инициализация имени и зарплаты.
     * @param name
     * @param salary
     */

    Teacher(String name, double salary) {
        super(name, salary);
    }

    /**
     * Проверка домашних заданий.
     * @param pupilList список учеников
     */
    public void checkHomework(Pupil[] pupilList) {
        for (Pupil pupil : pupilList) {
            String pupilName = pupil.getName();
            int mark = (int) (Math.random() * 5);
            System.out.println(pupilName + " получает " + mark);
        }
    }

    /**
     * Создание класса из списка учеников.
     * @param names имена
     * @param classNumber номер класса
     * @return список учеников класса
     */
    public Pupil[] createClass(String[] names, int classNumber) {
        pupilList = new Pupil[names.length];
        for (int i = 0; i < names.length; i++) {
            Pupil pupil = new Pupil(names[i], classNumber);
            pupilList[i] = pupil;
        }
        return pupilList;
    }
}
