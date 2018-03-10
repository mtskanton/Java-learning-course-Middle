package app.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для прохода по страницам, требующим аутентификацию.
 */
@Controller
public class SecurityController {

    @GetMapping("/authorised")
    public String authPage() {
        return "authorised";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/errors/403";
    }
}
