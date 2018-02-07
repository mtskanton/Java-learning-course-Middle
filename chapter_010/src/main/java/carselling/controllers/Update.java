package carselling.controllers;

import carselling.logic.DaoAdvertisement;
import carselling.logic.DaoBrand;
import carselling.logic.DaoCarcase;
import carselling.models.Advertisement;
import carselling.models.Brand;
import carselling.models.Carcase;
import carselling.models.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Сервлет обновления объявления
 */
public class Update extends HttpServlet {

    private String uploadPath;

    @Override
    public void init() throws ServletException {
        String directory = getServletContext().getInitParameter("upload-directory");
        uploadPath = getServletContext().getRealPath("") + File.separator + directory;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands", DaoBrand.getInstance().getAll());
        req.setAttribute("carcases", DaoCarcase.getInstance().getAll());

        int id = Integer.valueOf(req.getParameter("id"));
        Advertisement ad = DaoAdvertisement.getInstance().getById(id);
        req.setAttribute("ad", ad);
        req.getRequestDispatcher("WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advertisement ad = new Advertisement();
        ad.setId(Integer.valueOf(req.getParameter("id")));
        ad.setSold(req.getParameter("sold") != null);
        int brandId = Integer.valueOf(req.getParameter("brand"));
        ad.setBrand(new Brand(brandId));
        ad.setModel(req.getParameter("model"));
        ad.setDescription(req.getParameter("description"));
        ad.setYear(Integer.valueOf(req.getParameter("year")));
        Integer carcaseId = Integer.valueOf(req.getParameter("carcase"));
        ad.setCarcase(new Carcase(carcaseId));
        ad.setPrice(Integer.valueOf(req.getParameter("price")));
        Integer userId = Integer.valueOf(req.getParameter("user"));
        ad.setUser(new User(userId));
        ad.setDate(new Timestamp(new Date().getTime()));

        String ext = null;
        Part filePart = req.getPart("picture");
        String content = filePart.getHeader("content-disposition").trim().replace("\"", "");
        if (content.lastIndexOf(".") != -1 && content.lastIndexOf(".") != 0) {
            ext =  content.substring(content.lastIndexOf("."));
            try (InputStream filecontent = filePart.getInputStream();
                OutputStream out = new FileOutputStream(new File(uploadPath + File.separator + ad.getId() + ext))) {
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
        }
        DaoAdvertisement.getInstance().update(ad);

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
