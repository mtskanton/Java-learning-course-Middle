package crudservlet;

import java.util.Date;

/**
 * Класс пользователя.
 */
public class User {

    private String name, login, email;
    private Date createDate;

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
}
