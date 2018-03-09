package app.example.controller;

import app.example.model.Account;
import app.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер стартовой страницы.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("title", "Описание проекта");
        return "home";
    }

}
