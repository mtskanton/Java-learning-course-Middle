package ru.job4j.nonblocking;

import java.util.Objects;

/**
 * Класс модели для хранения в кэше
 */
public class Model {
    private int version = 0;
    private int id;
    private String name;

    Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getVersion() {
        return this.version;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
        this.version++;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return version == model.version && id == model.id && Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, id, name);
    }
}
