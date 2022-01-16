package Jobyssey.backend.dao;

import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private static final String UPDATE_COMPANY = new StringBuilder("")
            .append("UPDATE company set %1$s = %1$s + 1 WHERE company_name = '%2$s'")
            .toString();

    public void updateCompanyCount(String company, int where){
        if(where == 0){
            jdbcTemplate.update(String.format(UPDATE_COMPANY, "applications", company));
        } else {
            jdbcTemplate.update(String.format(UPDATE_COMPANY, "interviews", company));
        }
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

    public List<User> checkPassword() {
        String sql = "" +
                "SELECT " +
                "username, " +
                "password, " +
                "email " +
                "FROM user_resources";
      return jdbcTemplate.query(sql, mapUser());
    }

    private RowMapper<User> mapUser() {
        return (resultSet, i) -> {
            return new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email")
            );
        };
    }

    public User returnUser(String username) {
        String sql = "" +
                "SELECT * from user_resources where username = '?'";
        User oldUser;
        try {
            oldUser = jdbcTemplate.queryForObject(sql, new Object[]{username}, (resultSet, i) ->
                    new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("email")
                    ));
        } catch (Exception e){
            return null;
        }
        return oldUser;
    }
}
