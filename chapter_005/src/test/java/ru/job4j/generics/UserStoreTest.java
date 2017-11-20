package ru.job4j.generics;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {

    @Test
    public void whenAddUserThenItInTheList() {
        UserStore us = new UserStore(10);
        User user = new User();
        user.setId("123");
        user.setName("Name");
        us.add(user);
        User found = us.get("123");
        assertThat(found.getName(), is("Name"));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteUserThenItNoMoreInTheList() {
        UserStore us = new UserStore(10);
        User user = new User();
        user.setId("123");
        user.setName("Name");

        us.add(user);
        User found = us.get("123");
        assertThat(found.getName(), is("Name"));

        us.delete("123");
        User deleted = us.get("123");
        String name = deleted.getName();
    }

    @Test
    public void whenUpdateUserThenAnotherName() {
        UserStore us = new UserStore(10);
        User user = new User();
        user.setId("123");
        user.setName("Name");

        us.add(user);
        User found = us.get("123");
        assertThat(found.getName(), is("Name"));

        User changed = new User();
        changed.setId("123");
        user.setName("Name_updated");
        us.update(changed);
        assertThat(found.getName(), is("Name_updated"));
    }
}