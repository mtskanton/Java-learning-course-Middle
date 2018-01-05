package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Сервлет добавления пользователя.
 */
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter w = resp.getWriter();
        w.println("<b>Create new user</b>");
        w.println("<form action=" + req.getContextPath() + "/create method=post>");
        w.println("<table>");
        w.println("<tr><td>NAME</td><td><input type=text name=name></td></tr>");
        w.println("<tr><td>LOGIN</td><td><input type=text name=login></td></tr>");
        w.println("<tr><td>EMAIL</td><td><input type=text name=email></td></tr>");
        w.println("<tr><td colspan=2><input type=submit value=Submit></td></tr>");
        w.println("</table>");
        w.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        try {
            UsersStore.getInstance().addUser(name, login, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
