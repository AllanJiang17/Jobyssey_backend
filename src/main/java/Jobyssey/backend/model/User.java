package Jobyssey.backend.model;

public class User {

    private String username;
    private String password;
    private String email;
    private String applications;
    private String interviews;

    public User(String username, String password, String email, String applications, String interviews) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.applications = applications;
        this.interviews = interviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApplications() {
        return applications;
    }

    public void setApplications(String applications) {
        this.applications = applications;
    }

    public String getInterviews() {
        return interviews;
    }

    public void setInterviews(String interviews) {
        this.interviews = interviews;
    }
}
