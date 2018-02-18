package carselling.controller;

import carselling.logic.DaoAdvertisement;
import carselling.logic.DaoBrand;
import carselling.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.util.Collections;

/**
 * Отображение списка объявлений.
 */

@Controller
@RequestMapping(value = "list")
public class List {

    @Autowired
    private DaoAdvertisement ads;

    @Autowired
    private DaoBrand brands;

    @GetMapping
    public String getAll(Model model, @RequestParam(required = false) String today, @RequestParam(required = false) String brand) {
        model.addAttribute("brands", brands.getAll());
        java.util.List<Advertisement> list = ads.getAllFiltered(today, brand);
        Collections.sort(list);
        model.addAttribute("ads", list);
        return "list";
    }
}
