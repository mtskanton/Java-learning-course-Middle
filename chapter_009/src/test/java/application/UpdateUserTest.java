package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Тест на обновление информации о пользователе.
 */
public class UpdateUserTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Before
    public void init() {
        DbManager store = DbManager.getInstance();
        store.deleteAllUsers();
        store.addUser("name", "login", "password", "email", "Russia", "Moscow", "User");
    }

    /**
     * Тест на изменение данных и роли пользователя.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenSetNewUserDataThenUpdateIt() throws ServletException, IOException {

        List<User> added = DbManager.getInstance().getUsers();
        when(request.getParameter("id")).thenReturn(added.get(0).getId().toString());
        when(request.getParameter("name")).thenReturn("changedName");
        when(request.getParameter("login")).thenReturn("changedLogin");
        when(request.getParameter("password")).thenReturn("changedPass");
        when(request.getParameter("email")).thenReturn("changedEmail");
        when(request.getParameter("role")).thenReturn("Admin");

        UpdateUser update = new UpdateUser();
        update.doPost(request, response);

        List<User> users = DbManager.getInstance().getUsers();
        assertThat(users.get(0).getName(), is("changedName"));
        assertThat(users.get(0).getLogin(), is("changedLogin"));
        assertThat(users.get(0).getPassword(), is("changedPass"));
        assertThat(users.get(0).getEmail(), is("changedEmail"));
        assertThat(users.get(0).getRole(), is("Admin"));
    }

    /**
     * Изменяем пользователя используя пустой логин.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test(expected = Exception.class)
    public void whenUpdateWithEmptyLoginThenRedirectRequest() throws ServletException, IOException {

        List<User> added = DbManager.getInstance().getUsers();
        when(request.getParameter("id")).thenReturn(added.get(0).getId().toString());
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn(" ");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("role")).thenReturn("User");

        UpdateUser update = new UpdateUser();
        update.doPost(request, response);

        doThrow(Exception.class).when(dispatcher).forward(request, response);
    }
}
