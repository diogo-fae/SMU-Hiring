package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JobPosting {
    private String jobId;
    private String company;
    private String positionName;
    private String supervisorFirstName;
    private String supervisorLastName;
    private String supervisorPhoneNumber;
    private String supervisorEmail;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String payPerHour;
    private Map<String, List<String>> jobQualificationsList;

    public JobPosting() {
    }
    public JobPosting(String jobId, String company) {
        this.jobId = jobId;
        this.company = company;
    }
}
