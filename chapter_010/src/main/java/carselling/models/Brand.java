package carselling.models;

/**
 * Класс марки автомобиля.
 */
public class Brand {

    private int id;
    private String name;

    public Brand() { }

    public Brand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
