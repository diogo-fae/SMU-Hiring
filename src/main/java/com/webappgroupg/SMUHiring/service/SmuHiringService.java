package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.SmuHiringDatabaseOperations;
import com.webappgroupg.SMUHiring.model1.Cred;
import com.webappgroupg.SMUHiring.model1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmuHiringService {

    @Autowired
    private SmuHiringDatabaseOperations smuHiringDatabaseOperations;

    public void registerProfessional(ProfessionalRequest request) {
        smuHiringDatabaseOperations.registerProfessionalAccountRequest(request);
        smuHiringDatabaseOperations.registerProfessionalQualificationRequest(request.getProfessionalQualificationRequest());
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

    public Employer updateEmployer(Employer request) {
        return new Employer();
    }

    public void initiateJobMatching(String userId) {
    }

    public void postJob(JobPosting request) {
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
}
