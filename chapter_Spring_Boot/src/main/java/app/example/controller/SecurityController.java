package app.example.controller;

import app.example.form.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid RegistrationForm registrationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            if(result.getGlobalError() != null) {
                model.addAttribute("passwordsDontMatch", result.getGlobalError().getDefaultMessage());
            }
            return "register";
        }
        return "redirect:/";
    }
}
