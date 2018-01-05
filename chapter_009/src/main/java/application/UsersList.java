package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Сервлет получения списка пользователей.
 */
public class UsersList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter w = resp.getWriter();
        w.println("<b>Table of users</b>");
        w.println("<table border=1>");

        w.println("<tr>");
        w.println("<th> name </th>");
        w.println("<th> login </th>");
        w.println("<th> email </th>");
        w.println("<th> creation date </th>");
        w.println("<th></th>");
        w.println("<th></th>");
        w.println("</tr>");

        try {
            List<User> users = UsersStore.getInstance().getUsers();

            Collections.sort(users);

            for (User user : users) {
                w.println("<tr>");
                w.println("<td>" + user.getName() + "</td>");
                w.println("<td>" + user.getLogin() + "</td>");
                w.println("<td>" + user.getEmail() + "</td>");
                w.println("<td>" + user.getCreated() + "</td>");
                w.println("<td><a href= " + req.getContextPath() + "/update?id=" + user.getId() + " >EDIT</a></td>");
                w.println("<td><a href= " + req.getContextPath() + "/delete?id=" + user.getId() + " >DELETE</a></td>");
                w.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        w.println("</table>");

        w.println("<a href=http://localhost:8080/items/create>Add new user</a>");
    }
}
