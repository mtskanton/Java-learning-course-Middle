package app.example.controller;

import app.example.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CompanyController {

    @Autowired
    CompanyRepository companies;

}
