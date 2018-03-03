package carselling.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Модель - кузов автомобиля.
 */

@Entity
@Table(name = "carcases")
public class Carcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Carcase c = (Carcase) o;
        return (this.id == c.id && Objects.equals(type, c.type));
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        return result;
    }
}
