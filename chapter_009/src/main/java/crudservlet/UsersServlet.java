package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс сервлета для операций:
 * получения doGet
 * добавления doPost
 * изменения doPut
 * удаления doDelete
 */
public class UsersServlet extends HttpServlet {

    //получить список пользователей
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter w = resp.getWriter();
        w.println("<b>Table of users</b>");
        w.println("<table border=1>");

        w.println("<tr>");
        w.println("<td> name </td>");
        w.println("<td> login </td>");
        w.println("<td> email </td>");
        w.println("<td> creation date </td>");
        w.println("</tr>");

        try {
            List<User> users = UsersStore.getInstance().getUsers();

            for (User user : users) {
                w.println("<tr>");
                w.println("<td>" + user.getName() + "</td>");
                w.println("<td>" + user.getLogin() + "</td>");
                w.println("<td>" + user.getEmail() + "</td>");
                w.println("<td>" + user.getCreated() + "</td>");
                w.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        w.println("</table>");
    }

    //добавить пользователя
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name:");
        String login = req.getParameter("login:");
        String email = req.getParameter("email:");

        try {
            UsersStore.getInstance().addUser(name, login, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //изменить данные пользователя
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id:"));
        String name = req.getParameter("name:");
        String login = req.getParameter("login:");
        String email = req.getParameter("email:");

        try {
            UsersStore.getInstance().updateUser(id, name, login, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //удалить пользователя
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id:"));
        try {
            UsersStore.getInstance().deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
