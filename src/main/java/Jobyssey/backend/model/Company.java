package Jobyssey.backend.model;

public class Company {

    private String name;
    private int applications;
    private int interviews;

    public Company(String name, int applications, int interviews) {
        this.name = name;
        this.applications = applications;
        this.interviews = interviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApplications() {
        return applications;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }

    public int getInterviews() {
        return interviews;
    }

    public void setInterviews(int interviews) {
        this.interviews = interviews;
    }
}
