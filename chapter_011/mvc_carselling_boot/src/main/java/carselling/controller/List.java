package carselling.controller;

import carselling.logic.RepositoryAdvertisementCustom;
import carselling.logic.RepositoryBrand;
import carselling.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

/**
 * Отображение списка объявлений.
 */

@Controller
public class List {

    @Autowired
    private RepositoryAdvertisementCustom rac;

    @Autowired
    private RepositoryBrand brands;

    @GetMapping("/")
    public String startPage() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getAll(Model model, @RequestParam(required = false) String today, @RequestParam(required = false) String brand) {
        model.addAttribute("brands", brands.findAll());

        java.util.List<Advertisement> list = rac.getAllFiltered(today, brand);
        Collections.sort(list);
        model.addAttribute("ads", list);
        return "list";
    }

}
