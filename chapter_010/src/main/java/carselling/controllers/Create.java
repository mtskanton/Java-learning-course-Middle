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
 * Сервлет создания объявления
 */
public class Create extends HttpServlet {

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
        req.getRequestDispatcher("WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advertisement ad = new Advertisement();

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setFileSizeMax(maxFileSize);

        try {
            java.util.List<FileItem> parts = upload.parseRequest(req);

            //перебираем все простые поля
            for (FileItem item : parts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case("brand"):
                            Integer brandId = Integer.valueOf(item.getString());
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
                            ad.setPrice(Integer.valueOf(item.getString()));
                            break;
                        case("user"):
                            Integer userId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setUser(new User(userId));
                            break;
                        default:
                            break;
                    }
                }
            }

            ad.setDate(new Timestamp(new Date().getTime()));
            ad.setSold(false);
            DaoAdvertisement.getInstance().create(ad);

            //находим поле с загруженным файлом
            for (FileItem item : parts) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
