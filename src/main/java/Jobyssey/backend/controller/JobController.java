package Jobyssey.backend.controller;

import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import Jobyssey.backend.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @GetMapping(path = "{username}/{password}")
    public User checkPassword(@PathVariable("username") String username, @PathVariable("password") String password){
        return service.checkPassword(username, password);
    }

    @GetMapping("/getCompany")
    public List<Company> getAllCompanies() {
        return service.returnAllCompanies();
    }

    @PostMapping(path = "/updateUser")
    public void updateUserCompany(@RequestBody User user){
        service.addNewApplications(user);
    }

    @PostMapping(path = "{company}/{type}")
    public void updateCompany(@PathVariable("company") String company, @PathVariable("type") int which){
        service.updateCompanyCount(company, which);
    }

}
