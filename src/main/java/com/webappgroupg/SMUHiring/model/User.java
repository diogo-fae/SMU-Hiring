package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String status;
    private String userType;
    private List<Payment> paymentList;
    private boolean hasLoggedIn;

    public User(String userId, String firstName, String lastName, String email, String phoneNumber, String status, String userType, List<Payment> paymentList, boolean hasLoggedIn) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userType = userType;
        this.paymentList = paymentList;
        this.hasLoggedIn = hasLoggedIn;
    }

    public User(String userId, String firstName, String lastName, String email, String phoneNumber, String status, String userType, List<Payment> paymentList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userType = userType;
        this.paymentList = paymentList;
        this.hasLoggedIn = true;
    }

    public User() {
    }

}
