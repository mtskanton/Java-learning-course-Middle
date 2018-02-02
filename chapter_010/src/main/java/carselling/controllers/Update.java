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
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Сервлет обновления объявления
 */
public class Update extends HttpServlet {

    private String uploadPath;
    private int maxFileSize;

    @Override
    public void init() throws ServletException {
        String directory = getServletContext().getInitParameter("upload-directory");
        uploadPath = getServletContext().getRealPath("") + File.separator + directory;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        maxFileSize = Integer.valueOf(getServletContext().getInitParameter("max-file-size"));
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

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setFileSizeMax(maxFileSize);

        try {
            java.util.List<FileItem> parts = upload.parseRequest(req);

            for (FileItem item : parts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case("sold"):
                            ad.setSold(true);
                            break;
                        case("brand"):
                            Integer brandId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setBrand(new Brand(brandId));
                            break;
                        case("model"):
                            ad.setModel(item.getString("UTF-8"));
                            break;
                        case ("description"):
                            ad.setDescription(item.getString("UTF-8"));
                            break;
                        case("year"):
                            ad.setYear(Integer.valueOf(item.getString("UTF-8")));
                            break;
                        case("carcase"):
                            Integer carcaseId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setCarcase(new Carcase(carcaseId));
                            break;
                        case("price"):
                            ad.setPrice(Integer.valueOf(item.getString("UTF-8")));
                            break;
                        case("user"):
                            Integer userId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setUser(new User(userId));
                            break;
                        default:
                            break;
                    }
                }

                if (!item.isFormField()) {
                    if (item.getFieldName().equals("picture")) {
                        String fileName = item.getName();
                        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                            String ext =  fileName.substring(fileName.lastIndexOf("."));
                            item.write(new File(uploadPath + File.separator + ad.getId() + ext));
                        }
                    }
                }
            }

            ad.setDate(new Timestamp(new Date().getTime()));
            DaoAdvertisement.getInstance().update(ad);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
