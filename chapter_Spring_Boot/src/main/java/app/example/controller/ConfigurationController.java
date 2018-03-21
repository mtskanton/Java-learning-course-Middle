package app.example.controller;

import app.example.configuration.BasicProperties;
import app.example.configuration.SecondProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigurationController {

    @Autowired
    private BasicProperties basicProperties;

    @Autowired
    private SecondProperties secondProperties;

    @GetMapping("/configure")
    public String configure(Model model) {
        model.addAttribute("basicProperties", basicProperties);
        model.addAttribute("secondProperties", secondProperties);
        return "configure";
    }
}
