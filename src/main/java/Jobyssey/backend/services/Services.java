package Jobyssey.backend.services;

import Jobyssey.backend.dao.JobDao;
import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {

    @Autowired
    JobDao jobDao;

    public List<Company> returnAllCompanies(){
        return jobDao.returnAllCompany();
    }

    public void addNewCompany(Company company) {
        jobDao.addCompany(company);
    }

    public void addNewUser(User user) {
        jobDao.addUser(user);
    }

}
