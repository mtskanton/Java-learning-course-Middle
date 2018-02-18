package carselling.controller;

import carselling.logic.DaoAdvertisement;
import carselling.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Удаление объявления.
 */
@Controller
public class Delete {

    @Autowired
    private DaoAdvertisement ads;

    @GetMapping
    @RequestMapping(value = "delete/{id}")
    public String deleteAdvertisement(@PathVariable("id") int id) {
        ads.delete(new Advertisement(id));
        return "redirect:list";
    }
}
