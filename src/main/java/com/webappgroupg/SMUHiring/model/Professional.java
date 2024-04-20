package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Professional {
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
    private String university;
    private String graduationDate;
    private String degreeType;

    public Professional(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String university, String graduationDate, String degreeType) {
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
        this.university = university;
        this.graduationDate = graduationDate;
        this.degreeType = degreeType;
    }

    // No address2
    public Professional(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String city, String state, int zipCode, String university, String graduationDate, String degreeType) {
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
        this.university = university;
        this.graduationDate = graduationDate;
        this.degreeType = degreeType;
    }

    public Professional(String userId) {
        this.userId = userId;
    }

    public Professional() {

    }

    @Override
    public String toString() {
        return "Professional{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", status='" + status + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", university='" + university + '\'' +
                ", graduationDate='" + graduationDate + '\'' +
                ", degreeType='" + degreeType + '\'' +
                '}';
    }
}
