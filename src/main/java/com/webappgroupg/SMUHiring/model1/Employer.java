package com.webappgroupg.SMUHiring.model1;

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
}
