package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.SmuHiringDatabaseOperations;
import com.webappgroupg.SMUHiring.model1.Cred;
import com.webappgroupg.SMUHiring.model1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmuHiringService {

    @Autowired
    private SmuHiringDatabaseOperations smuHiringDatabaseOperations;

    public void registerProfessional(ProfessionalRequest request) {
        smuHiringDatabaseOperations.registerProfessionalAccountRequest(request);
        smuHiringDatabaseOperations.registerProfessionalQualificationRequest(request.getUserId(), request.getProfessionalQualificationRequest());
    }

    public void registerEmployer(EmployerRequest request) {
        smuHiringDatabaseOperations.registerEmployerAccountRequest(request);
    }

    public Object login(Cred cred) {
        return smuHiringDatabaseOperations.getLoginDetails(cred);
    }


    public Professional updateProfessional(Professional request) {
        return smuHiringDatabaseOperations.updateProfessional(request);
    }

    public JobPosting getJobInfo(int jobId, String company) {
        return smuHiringDatabaseOperations.getJobInfo(jobId, company);
    }

    public Employer updateEmployer(Employer request) {
        return smuHiringDatabaseOperations.updateEmployer(request);
    }

    public void initiateJobMatching(String userId) {
        smuHiringDatabaseOperations.initiateJobMatching(userId);
    }

    public void postJob(JobPosting request) {
        smuHiringDatabaseOperations.postJob(request);
    }



    public Payment makePayment(Payment payment) {
        return smuHiringDatabaseOperations.makePayment(payment);
    }

    public void changePassword(Cred cred) {
        smuHiringDatabaseOperations.changePassword(cred);
    }

    public void deleteAccount(String userId) {
        smuHiringDatabaseOperations.requestAccountDeletion(userId);
    }

    public void createProfessional(ProfessionalRequest request) {
    }

    public void createEmployer(EmployerRequest request) {
    }

    public String getNumberOfRequests(String userType) {
        return "";
    }

    public boolean approveAccountDeletion(String userId) {
        return smuHiringDatabaseOperations.approveAccountDeletion(userId);
    }

    public void createStaff(User request) {
    }

    public void deleteJob(JobPosting request) {
        smuHiringDatabaseOperations.deleteJob(request);
    }

    public JobPosting updateJob(JobPosting request) {
        return smuHiringDatabaseOperations.updateJob(request);
    }

    public void requestUserDelete(String userId) {
        smuHiringDatabaseOperations.requestUserDelete(userId);
    }

    public Employer getEmployerInfo(String userId) {
        return smuHiringDatabaseOperations.getEmployerInfo(userId);
    }

    public List<JobPosting> getAllJobs() {
        return smuHiringDatabaseOperations.getAllJobs();
    }

    public List<JobPosting> getAllJobs(String company) {
        return smuHiringDatabaseOperations.getAllJobs(company);
    }

    public Professional getProfessionalInfo(String userId) {
        return smuHiringDatabaseOperations.getProfessionalInfo(userId);
    }
}
