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

}
