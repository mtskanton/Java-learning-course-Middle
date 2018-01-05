package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Сервлет удаления пользователя.
 */
public class DeleteUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        try {
            UsersStore.getInstance().deleteUser(id);
            resp.sendRedirect(req.getContextPath() + "/list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
