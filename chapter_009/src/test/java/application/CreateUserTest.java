package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
 * Тест на добавление пользователя.
 */
public class CreateUserTest {

    @Before
    public void init() {
        DbManager.getInstance().deleteAllUsers();
    }

    /**
     * Добавление пользователя.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenAddUserThenItIsAddedToDb() throws ServletException, IOException {

        CreateUser create = new CreateUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("testName");
        when(request.getParameter("login")).thenReturn("testLogin");
        when(request.getParameter("password")).thenReturn("testPass");
        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("role")).thenReturn("User");

        create.doPost(request, response);
        List<User> users = DbManager.getInstance().getUsers();
        assertThat(users.get(0).getName(), is("testName"));
        assertThat(users.get(0).getLogin(), is("testLogin"));
        assertThat(users.get(0).getPassword(), is("testPass"));
        assertThat(users.get(0).getEmail(), is("testEmail"));
        assertThat(users.get(0).getRole(), is("User"));
    }

    /**
     * Создание пользователя с занятым логином.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test(expected = Exception.class)
    public void whenAddLoginWithUsedLoginThenRedirectRequest() throws ServletException, IOException {

        CreateUser create = new CreateUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("role")).thenReturn("User");

        create.doPost(request, response);

        when(request.getParameter("name")).thenReturn("anotherName");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("anotherPass");
        when(request.getParameter("role")).thenReturn("User");

        create.doPost(request, response);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        doThrow(Exception.class).when(dispatcher).forward(request, response);
    }

    /**
     * Создание пользователя с пустым логином.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test(expected = Exception.class)
    public void whenEmptyLoginThenRedirectRequest() throws ServletException, IOException {

        CreateUser create = new CreateUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn(" ");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("role")).thenReturn("User");

        create.doPost(request, response);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        doThrow(Exception.class).when(dispatcher).forward(request, response);
    }
}
