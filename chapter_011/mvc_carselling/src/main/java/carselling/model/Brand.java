package carselling.model;

import java.util.Objects;

/**
 * Модель - марка автомобиля.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Brand b = (Brand) o;
        return id == b.id && Objects.equals(name, b.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (this.name == null ? 0 : name.hashCode());
        return result;
    }
}
