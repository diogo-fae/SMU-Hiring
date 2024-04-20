package com.webappgroupg.SMUHiring.model1;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobPosting {
    private int jobId;
    private String company;
    private String positionName;
    private String supervisorName;
    private String supervisorEmail;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private double payPerHour;
    private List<JobQualifications> jobQualificationsList;

}
