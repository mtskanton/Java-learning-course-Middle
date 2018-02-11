package carselling.logic;

import carselling.models.User;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DaoUserTest {

    private final DaoUser users = DaoUser.getInstance();

    @Test
    public void testCreateAndGetById() {
        User user = new User();
        user.setName("name");
        user.setLogin("root");
        user.setPassword("root");
        user.setPhone(88002000600L);
        this.users.create(user);
        User result = users.getById(user.getId());
        assertThat(user, is(result));
    }

    @Test
    public void testIsRegistered() {
        User user = new User();
        user.setName("name");
        user.setLogin("login");
        user.setPassword("pass");
        user.setPhone(88002000600L);
        this.users.create(user);
        User result = users.isRegistered("login", "pass");
        assertThat(user, is(result));
    }
}
