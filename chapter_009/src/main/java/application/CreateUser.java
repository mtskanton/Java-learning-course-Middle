package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет добавления пользователя.
 */
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", DbManager.INSTANCE.getRoles());
        req.setAttribute("countries", DbManager.INSTANCE.getCountries());
        req.setAttribute("cities", DbManager.INSTANCE.getCities());
        req.getRequestDispatcher("WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String role = req.getParameter("role");

        if (login.trim().equals("") | password.trim().equals("")) {
            req.setAttribute("error", "Login and password could not be blank.");
            doGet(req, resp);
        } else if (!DbManager.INSTANCE.loginIsFree(-1, login)) {
            req.setAttribute("error", "Login is already in use. Please choose another one.");
            doGet(req, resp);
        } else {
            DbManager.INSTANCE.addUser(name, login, password, email, country, city, role);
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}