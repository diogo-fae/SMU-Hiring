package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String status;
    private Character userType;

    public User(String userId, String firstName, String lastName, String email, long phoneNumber, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
    public User(String userId, String firstName, String lastName, String email, long phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = "active";
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(Employer employer){
        this.userId = employer.getUserId();
        this.firstName = employer.getFirstName();
        this.lastName = employer.getLastName();
        this.email = employer.getEmail();
        this.phoneNumber = employer.getPhoneNumber();
        this.status = employer.getStatus();
        this.userType = 'E';
    }
    public User(Professional professional){
        this.userId = professional.getUserId();
        this.firstName = professional.getFirstName();
        this.lastName = professional.getLastName();
        this.email = professional.getEmail();
        this.phoneNumber = professional.getPhoneNumber();
        this.status = professional.getStatus();
        this.userType = 'P';
    }
}
