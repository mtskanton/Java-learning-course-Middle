package application;

import java.util.Date;

/**
 * Класс пользователя.
 */
public class User implements Comparable<User> {

    private int id;
    private String name, login, email;
    private Date createDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated(Date created) {
        this.createDate = created;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreated() {
        return this.createDate;
    }

    @Override
    public int compareTo(User user) {
        return this.getName().compareTo(user.getName());
    }
}
