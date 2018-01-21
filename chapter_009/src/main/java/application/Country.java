package application;

/**
 * Класс страны.
 */
public class Country implements Comparable<Country> {

    private int id;
    private String name;

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

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }
}
