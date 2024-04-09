package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

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

    public JobPosting(int jobId, String company, String positionName, String supervisorName, String supervisorEmail, String startDate, String endDate, String startTime, String endTime, double payPerHour) {
        this.jobId = jobId;
        this.company = company;
        this.positionName = positionName;
        this.supervisorName = supervisorName;
        this.supervisorEmail = supervisorEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.payPerHour = payPerHour;
    }

    public JobPosting(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                "jobId=" + jobId +
                ", company='" + company + '\'' +
                ", positionName='" + positionName + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                ", supervisorEmail='" + supervisorEmail + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", payPerHour=" + payPerHour +
                '}';
    }
}
