package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        req.setAttribute("users", UsersStore.getInstance().getUsers());
        req.setAttribute("path", req.getContextPath());
        if (req.getSession().getAttribute("role").equals("User")) {
            req.setAttribute("access", "disabled");
        }
        req.getRequestDispatcher("WEB-INF/views/list.jsp").forward(req, resp);
    }
}
