package carselling.controller;

import carselling.logic.RepositoryAdvertisement;
import carselling.logic.RepositoryBrand;
import carselling.logic.RepositoryCarcase;
import carselling.model.Advertisement;
import carselling.model.Brand;
import carselling.model.Carcase;
import carselling.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Создание объявления.
 */
@Controller
@RequestMapping(value = "/create")
public class Create {

    @Autowired
    private RepositoryAdvertisement ads;

    @Autowired
    private RepositoryBrand brands;

    @Autowired
    private RepositoryCarcase carcases;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public String getCreateForm(Model model) {
        model.addAttribute("brands", brands.findAll());
        model.addAttribute("carcases", carcases.findAll());
        return "create";
    }

    @PostMapping
    public String createAdvertisement(@Validated Advertisement advertisement,
                                      @RequestParam("brand_id") int brandId,
                                      @RequestParam("carcase_id") int carcaseId,
                                      @RequestParam("user_id") int userId,
                                      @RequestParam("picture") MultipartFile file) {

        advertisement.setBrand(new Brand(brandId));
        advertisement.setCarcase(new Carcase(carcaseId));
        advertisement.setUser(new User(userId));
        advertisement.setDate(new Timestamp(new Date().getTime()));
        advertisement.setSold(false);
        ads.save(advertisement);

        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
                    String ext =  filename.substring(filename.lastIndexOf("."));
                    File dir = new File(servletContext.getRealPath("") + File.separator + "pic");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File uploadedFile = new File(dir.getAbsolutePath() + File.separator + advertisement.getId() + ext);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    byte[] bytes = file.getBytes();
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/list";
    }
}
