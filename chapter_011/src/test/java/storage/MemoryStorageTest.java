package storage;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import storage.logic.MemoryStorage;
import storage.model.User;

/**
 * Класс тестирования MemoryStorage.
 */
public class MemoryStorageTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("storage.xml");
    private final MemoryStorage memoryStorage = (MemoryStorage) context.getBean("memoryStorage");
    private int index = 0;

    @Test
    public void whenAddUserThenInTheList() {
        User user = new User();
        user.setId(++index);
        user.setName("Mike");
        user.setEmail("mike@mail.ru");
        this.memoryStorage.create(user);
        User result = this.memoryStorage.getById(user.getId());
        assertSame(result, user);
    }

    @Test
    public void whenUpdateThenChangedUser() {
        User user = new User();
        user.setId(++index);
        user.setName("Mike");
        user.setEmail("mike@mail.ru");
        this.memoryStorage.create(user);
        System.out.println(this.memoryStorage.getAll().size());
        User result = this.memoryStorage.getById(user.getId());
        assertThat(result.getName(), is("Mike"));
        assertThat(result.getEmail(), is("mike@mail.ru"));
        user.setName("Changed");
        user.setEmail("changed@mail.ru");
        this.memoryStorage.update(user);
        User changed = this.memoryStorage.getById(user.getId());
        assertThat(changed.getName(), is("Changed"));
        assertThat(changed.getEmail(), is("changed@mail.ru"));
    }

    @Test
    public void whenDeleteUserThenNotExists() {
        User user = new User();
        user.setId(++index);
        user.setName("Bad");
        user.setEmail("bad@mail.ru");
        this.memoryStorage.create(user);
        User result = this.memoryStorage.getById(user.getId());
        assertSame(result, user);
        this.memoryStorage.delete(user.getId());
        assertNull(this.memoryStorage.getById(user.getId()));
    }
}
