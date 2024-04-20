package com.webappgroupg.SMUHiring.model1;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
    private List<ProfessionalQualifications> professionalQualificationsList;
}
