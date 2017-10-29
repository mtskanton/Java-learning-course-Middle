package ru.job4j.applications;

/**
 * Класс единицы заявки.
 */
public class Item {
    /**
     * Уникальный номер заявки.
     */
    private String id;
    /**
     * Имя заявки.
     */
    private String name;
    /**
     * Описание заявки.
     */
    private String desc;
    /**
     * Дата создания.
     */
    private long created;
    /**
     * Список комментариев.
     */
    private String[] comments = new String[30];

    /**
     * Конструктор с инициализацией значений.
     * @param name имя
     * @param description описание
     * @param comment комментарий
     */
    public Item(String name, String description, String comment) {
        this.name = name;
        this.desc = description;
        this.created = System.currentTimeMillis();
        this.comments[0] = comment;
    }

    /**
     * Присвоение id.
     * @param generatedId генерируемый id
     */
    public void setId(String generatedId) {
        this.id = generatedId;
    }

    /**
     * Получение id.
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Присвоение имени.
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение имени.
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Присвоение описания.
     * @param desc описание
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }

    /**
     * Получение описания.
     * @return описание
     */
    public String getDescription() {
        return this.desc;
    }

    /**
     * Получение даты создания.
     * @return
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * ПОлучение списка комментариеы.
     * @return список комментариев
     */
    public String[] getComments() {
        return this.comments;
    }
}
