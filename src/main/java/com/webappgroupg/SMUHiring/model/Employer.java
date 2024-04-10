package com.webappgroupg.SMUHiring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employer {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipCode;
    private String company;

    public Employer(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String company) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.company = company;
    }

    // No address2
    public Employer(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String city, String state, int zipCode, String company) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.address1 = address1;
        this.address2 = "N/A";
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.company = company;
    }

    public Employer(String userId) {this.userId = userId;}
    public Employer(Employer givenEmployer){
        this.userId = givenEmployer.getUserId();
        this.firstName = givenEmployer.getFirstName();
        this.lastName = givenEmployer.getLastName();
        this.email = givenEmployer.getEmail();
        this.phoneNumber = givenEmployer.getPhoneNumber();
        this.status = givenEmployer.getStatus();
        this.address1 = givenEmployer.getAddress1();
        this.address2 = givenEmployer.getAddress2();
        this.city = givenEmployer.getCity();
        this.state = givenEmployer.getState();
        this.zipCode = givenEmployer.getZipCode();
        this.company = givenEmployer.getCompany();
    }

    @Override
    public String toString() {
        return "Employer{" + "userId='" + userId + "'}";
    }
}
