package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Тест на eудаление пользователя.
 */
public class DeleteUserTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Before
    public void init() {
        UsersStore store = UsersStore.getInstance();
        store.deleteAllUsers();
        store.addUser("name", "login", "password", "email", "User");
    }

    /**
     * Тест на удаление пользователя.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenSetNewUserDataThenUpdateIt() throws ServletException, IOException {

        List<User> added = UsersStore.getInstance().getUsers();
        when(request.getParameter("id")).thenReturn(added.get(0).getId().toString());
        
        DeleteUser delete = new DeleteUser();
        delete.doGet(request, response);

        List<User> users = UsersStore.getInstance().getUsers();
        assertThat(users.size(), is(0));
    }
}
