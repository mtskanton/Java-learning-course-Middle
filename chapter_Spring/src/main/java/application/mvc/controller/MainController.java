package application.mvc.controller;

import application.mvc.logic.DaoUser;
import application.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    DaoUser daoUser;

    @GetMapping
    @RequestMapping(value = "getAllUsers")
    public ModelAndView getAllUsers() {
        List<User> users = daoUser.getAll();
        return new ModelAndView("/jdbc/jdbc", "users", users);
    }
}
