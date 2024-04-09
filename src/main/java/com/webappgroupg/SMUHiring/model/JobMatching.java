package com.webappgroupg.SMUHiring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobMatching {
    private String jobId;
    private String userId;
    private String company;

    public JobMatching(String jobId, String userId, String company) {
        this.jobId = jobId;
        this.userId = userId;
        this.company = company;
    }
    public JobMatching(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JobMatching{" +
                "jobId='" + jobId + '\'' +
                ", userId='" + userId + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
