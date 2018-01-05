package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Сервлет обновления пользователя.
 */
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        User user = null;
        try {
            user = UsersStore.getInstance().getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter w = resp.getWriter();
        w.println("<b>Change user information</b>");
        w.println("<form action=" + req.getContextPath() + "/update method=post>");
        w.println("<table>");
        w.println("<tr><td>NAME</td><td><input type=text name=name value=" + (user == null ? " " : user.getName()) + "></td></tr>");
        w.println("<tr><td>LOGIN</td><td><input type=text name=login value=" + (user == null ? " " : user.getLogin()) + "></td></tr>");
        w.println("<tr><td>EMAIL</td><td><input type=text name=email value=" + (user == null ? " " : user.getEmail()) + "></td></tr>");
        w.println("<tr><td colspan=2><input type=hidden name=id value=" + (user == null ? " " : user.getId()) + "><input type=submit value=Submit></td></tr>");
        w.println("</table>");
        w.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        try {
            UsersStore.getInstance().updateUser(id, name, login, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
