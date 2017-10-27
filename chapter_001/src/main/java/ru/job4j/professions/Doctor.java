package ru.job4j.professions;

/**
 * Класс профессии доктор.
 */
public class Doctor extends Profession {
    /**
     * Инициализация имени и зарплаты.
     * @param name
     * @param salary
     */
    Doctor(String name, double salary) {
        super(name, salary);
    }
    /**
     * Заказ медикаментов.
     * @param diagnose диагноз
     * @return лекарство
     */
    public Medicine orderMed(String diagnose) {
        Medicine med = new Medicine();
        switch (diagnose) {
            case ("головная боль"):
                med.title = "аспирин";
                break;
            case ("тошнота"):
                med.title = "смекта";
                break;
            default:
                med.title = "плацебо";
        }
        return med;
    }

    /**
     * Лечение больного.
     * @param med лекарство
     * @param patient пациент
     */
    public void cure(Medicine med, Patient patient) {
        System.out.println("Доктор " + this.getName() + " использует " + med.title + ", чтобы вылечить пациента по имени " + patient.getName());
    }
}
