package application;

import java.util.Date;

/**
 * Класс пользователя.
 */
public class User implements Comparable<User> {

    private int id;
    private String name, login, email, password, role;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated(Date created) {
        this.createDate = created;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreated() {
        return this.createDate;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public int compareTo(User user) {
        return this.getName().compareTo(user.getName());
    }
}
