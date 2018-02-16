package quickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String showItems(Model model) {
        model.addAttribute("message", "Это пример Spring MVC");
        return "quickstart";
    }
}
