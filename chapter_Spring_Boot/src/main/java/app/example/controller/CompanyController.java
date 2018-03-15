package app.example.controller;

import app.example.model.Company;
import app.example.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyRepository companies;

    // /companies
    // /companies.xml
    // /companies.json
    @GetMapping(value = "/companies", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Company> getCompanies() {
        return (List<Company>) companies.findAll();
    }

    @GetMapping(value = "/company/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Company getCompanyById(@PathVariable("id") String id) {
        return companies.findOne(Integer.valueOf(id));
    }

    //Create
    @PostMapping(value = "/company", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void create(@RequestBody Company company) {
        companies.save(company);
    }

    //Update
    @PutMapping(value = "/company", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void update(@RequestBody Company company) {
        companies.save(company);
    }

    //Delete
    @DeleteMapping(value = "/company/{id}")
    public void delete(@PathVariable("id") String id) {
        companies.delete(Integer.valueOf(id));
    }
}
