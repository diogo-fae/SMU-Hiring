package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
public class Professional {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String university;
    private String graduationDate;
    private String degreeType;
    private Map<String, List<String>> professionalQualificationsList;
}
