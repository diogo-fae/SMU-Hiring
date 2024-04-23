package com.webappgroupg.SMUHiring.model1;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JobPosting {
    private int jobId;
    private String company;
    private String positionName;
    private String supervisorFirstName;
    private String supervisorLastName;
    private Long supervisorPhoneNumber;
    private String supervisorEmail;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private double payPerHour;
    private Map<String, List<String>> jobQualificationsList;

    public JobPosting() {
    }
    public JobPosting(int jobId, String company) {
        this.jobId = jobId;
        this.company = company;
    }
}
