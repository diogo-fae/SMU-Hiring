package com.webappgroupg.SMUHiring.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Staff {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String status;

    public Staff(String userId, String firstName, String lastName, String email, int phoneNumber, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
    public Staff(String userId, String firstName, String lastName, String email, int phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = "active";
    }
}
