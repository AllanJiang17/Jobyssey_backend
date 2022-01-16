package Jobyssey.backend.controller;

import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import Jobyssey.backend.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/company")
@RestController
public class JobController {

    @Autowired
    Services service;

    @PostMapping(path = "/company")
    public void InsertNewCompany(@RequestBody Company company){
        service.addNewCompany(company);
    }

    @PostMapping(path = "/user")
    public void InsertNewUser(@RequestBody User user){
        service.addNewUser(user);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return service.returnAllCompanies();
    }

}
