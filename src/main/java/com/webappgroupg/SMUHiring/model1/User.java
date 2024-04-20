package com.webappgroupg.SMUHiring.model1;


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
    private long phoneNumber;
    private String status;
    private Character userType;
    private List<Payment> paymentList;

}
