package Jobyssey.backend.dao;

import Jobyssey.backend.model.Company;
import Jobyssey.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
                "email, " +
                "interviews, " +
                "applications) " +
                "VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getInterviews(), user.getApplications());
    }

    public List<User> checkPassword() {
        String sql = "" +
                "SELECT " +
                "username, " +
                "password, " +
                "email, " +
                "interviews, " +
                "applications " +
                "FROM user_resources";
      return jdbcTemplate.query(sql, mapUser());
    }

    private RowMapper<User> mapUser() {
        return (resultSet, i) -> {
            return new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("interviews"),
                    resultSet.getString("applications")
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
                            resultSet.getString("email"),
                            resultSet.getString("applications"),
                            resultSet.getString("interviews")
                    ));
        } catch (Exception e){
            return null;
        }
        return oldUser;
    }

    private static final String ADD_APPLICATIONS = new StringBuilder("")
            .append("UPDATE user_resources set interviews = '%1$s', applications = '%2$s' WHERE username = '%3$s'")
            .toString();

    public int addNewApplications(User s) {
        return  jdbcTemplate.update(String.format(ADD_APPLICATIONS, s.getInterviews(), s.getApplications(), s.getUsername()));
    }

    private static final String GET_COMPANY_APPLICATION = new StringBuilder("")
            .append("SELECT * from company WHERE company_name = '%1$s'")
            .toString();

    public Company getCompanyApplication(String companyName) {
        return jdbcTemplate.queryForObject(String.format(GET_COMPANY_APPLICATION, companyName),
                new Object[]{companyName}, (rs, i)->
                new Company(
                        rs.getString("company_name"),
                        rs.getInt("applications"),
                        rs.getInt("interviews")
                ));
    }
}
