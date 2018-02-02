package carselling.controllers;

import carselling.logic.DaoAdvertisement;
import carselling.models.Advertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Сервлет списка объявлений
 */
public class List extends HttpServlet {

    private String uploadPath;

    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getInitParameter("upload-directory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("path", req.getContextPath());
        req.setAttribute("uploadPath", req.getContextPath() + File.separator + uploadPath + File.separator);
        java.util.List<Advertisement> list = DaoAdvertisement.getInstance().getAll();
        Collections.sort(list);
        req.setAttribute("ads", list);
        req.getRequestDispatcher("WEB-INF/views/list.jsp").forward(req, resp);
    }
}
