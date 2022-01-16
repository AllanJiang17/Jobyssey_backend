package Jobyssey.backend.dao;

import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private static final String UPDATE_COMPANY = new StringBuilder("")
            .append("UPDATE company set %1$s = %1$s + 1")
            .toString();

    public void updateCompanyCount(String company, int where){
        if(where == 0){
            jdbcTemplate.update(String.format(UPDATE_COMPANY, "application"));
        } else {
            jdbcTemplate.update(String.format(UPDATE_COMPANY, "interview"));
        }
    }

    public void createCompany(String company){

    }

    public List<Company> returnAllCompany(){
        String sql = "" +
                "SELECT " +
                "company_name, " +
                "applications, " +
                "interviews " +
                "FROM company";
        return jdbcTemplate.query(sql, mapCompany());
    }

    private RowMapper<Company> mapCompany() {
        return (resultSet, i) -> {
            return new Company(
                    resultSet.getString("company_name"),
                    resultSet.getInt("applications"),
                    resultSet.getInt("interviews")
            );
        };
    }

    public int addCompany(Company company) {
        String sql = "" +
                "INSERT INTO company (" +
                "company_name, " +
                "applications, " +
                "interviews) " +
                "VALUES (?,?,?)";
        return jdbcTemplate.update(sql, company.getName(), company.getApplications(), company.getInterviews());
    }

    public int addUser(User user) {
        String sql = "" +
                "INSERT INTO user_resources (" +
                "username, " +
                "password, " +
                "email) " +
                "VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

}
