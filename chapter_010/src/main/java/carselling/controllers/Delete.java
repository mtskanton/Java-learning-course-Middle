package carselling.controllers;

import carselling.logic.DaoAdvertisement;
import carselling.models.Advertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет удаления объявления
 */
public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        DaoAdvertisement.getInstance().delete(new Advertisement(id));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
