package configuration.annotations;

import org.springframework.beans.factory.annotation.Required;

/**
 * Сущность пользователя.
 */
public class User {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Required
    public void setAge(int age) {
        this.age = age;
    }
}
