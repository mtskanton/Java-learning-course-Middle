package ru.job4j.dom;

/**
 * Класс заявки
 */
public class Request {

    private String name;
    private String surname;
    private String date;
    private String title;
    private String text;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Request{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", date='" + date + '\''
                + ", title='" + title + '\''
                + ", text='" + text + '\''
                + '}';
    }
}
