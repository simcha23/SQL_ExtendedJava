package com.umuzi.SpringJpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer EmployeeID;

    private String FirstName;

    private String LastName;

    private String Email;

    private String JobTitle;

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }
}
