package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/second")
    public String goToSecondPage() {
        return "second";
    }

    @GetMapping("/admin")
    public String goToAdminPage() {
        return "admin";
    }
}
