package carselling.models;

/**
 * Класс кузова автомобиля.
 */
public class Carcase {

    private int id;
    private String type;

    public Carcase() { }

    public Carcase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
