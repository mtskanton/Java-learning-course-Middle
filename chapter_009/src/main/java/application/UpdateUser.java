package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обновления пользователя.
 */
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        User user = DbManager.getInstance().getUser(id);
        System.out.println(user.getCity());
        req.setAttribute("user", user);
        req.setAttribute("roles", DbManager.getInstance().getRoles());
        req.setAttribute("countries", DbManager.getInstance().getCountries());
        req.setAttribute("cities", DbManager.getInstance().getCitiesForCountry(user.getCountry()));
        if (req.getSession().getAttribute("role").equals("User")) {
            req.setAttribute("access", "disabled");
        }
        req.getRequestDispatcher("WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
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
        } else if (!DbManager.getInstance().loginIsFree(id, login)) {
            req.setAttribute("error", "Login is already in use. Please choose another one.");
            doGet(req, resp);
        } else {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setCountry(country);
            user.setCity(city);
            user.setRole(role);

            DbManager.getInstance().updateUser(user);
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
