package com.webappgroupg.SMUHiring.model1;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Getter
@Setter
public class ProfessionalRequest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String university;
    private String graduationDate;
    private String degreeType;
    private List<ProfessionalQualificationRequest> professionalQualificationRequest;

}
