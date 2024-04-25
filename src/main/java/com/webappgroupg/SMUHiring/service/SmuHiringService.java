package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.SmuHiringDatabaseOperations;
import com.webappgroupg.SMUHiring.model.Cred;
import com.webappgroupg.SMUHiring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

@Component
public class SmuHiringService {

    @Autowired
    private SmuHiringDatabaseOperations smuHiringDatabaseOperations;

    public boolean registerProfessional(ProfessionalRequest request) {
       return smuHiringDatabaseOperations.registerProfessionalAccountRequest(request);
    }

    public boolean registerEmployer(EmployerRequest request) {
        return smuHiringDatabaseOperations.registerEmployerAccountRequest(request);
    }

    public User login(Cred cred) {
        return smuHiringDatabaseOperations.getLoginDetails(cred);
    }


    public Professional updateProfessional(Professional request) {
        return smuHiringDatabaseOperations.updateProfessional(request);
    }

    public JobPosting getJobInfo(String jobId, String company) {
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
        smuHiringDatabaseOperations.createEmployer(request);
    }

    public boolean approveAccountDeletion(String userId) {
        return smuHiringDatabaseOperations.approveAccountDeletion(userId);
    }

    public void denyDeleteRequest(String userId) {
        smuHiringDatabaseOperations.removeAccountDeletionRequest(userId);
    }

    public void createStaff(User request) {
        smuHiringDatabaseOperations.createUser(request.getUserId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), "S");
        smuHiringDatabaseOperations.addCredentials(request.getUserId());
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

    public List<JobPosting> getAllJobs(String company) {
        return smuHiringDatabaseOperations.getAllJobs(company);
    }

    public Professional getProfessionalInfo(String userId) {
        return smuHiringDatabaseOperations.getProfessionalInfo(userId);
    }

    public User getStaffInfo(String userId) {
        return smuHiringDatabaseOperations.getUser(userId);
    }

    public void updateStaff(User request) {
        smuHiringDatabaseOperations.updateUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getUserId());
    }

    public List<Employer> getAllEmployers() {
        return smuHiringDatabaseOperations.getAllEmployers();
    }

    public List<Professional> getAllProfessionals() {
        return smuHiringDatabaseOperations.getAllProfessionals();
    }

    public List<JobMatchingRequest> getJobMatchingRequests() {
        return smuHiringDatabaseOperations.getJobMatchingRequests();
    }

    public EmployerRequest approveCreateEmployerRequest(String userId) {
        return smuHiringDatabaseOperations.approveCreateEmployerRequest(userId);
    }

    public List<JobPosting> getAllJobsForProfessional() {
        return smuHiringDatabaseOperations.getAllJobs();
    }

    public List<EmployerRequest> getCreateEmployerRequests() {
        return smuHiringDatabaseOperations.getCreateEmployerRequests();
    }

    public List<ProfessionalRequest> getCreateProfessionalRequests() {
        return smuHiringDatabaseOperations.getCreateProfessionalRequests();
    }

    public void approveCreateProfessionalRequest(String userId) {
        smuHiringDatabaseOperations.approveCreateProfessionalRequest(userId);
    }

    public void userLoggedIn(String userId) {
        smuHiringDatabaseOperations.userLoggedIn(userId);
    }

    public void denyCreateEmployerRequest(String userId) {
        EmployerRequest employerRequest = smuHiringDatabaseOperations.getCreateEmployerRequest(userId);
        smuHiringDatabaseOperations.removeEmployerCreateRequest(userId);
        String content = "We regret to inform that the employer qualification requirements does not satisfy our criteria for \n" +
                "username: " + employerRequest.getUserId() + "\n.";
        smuHiringDatabaseOperations.sendEmail(employerRequest.getEmail(), "Employer Registration Denied", content);
    }

    public void denyCreateProfessionalRequest(String userId) {
        ProfessionalRequest professionalRequest = smuHiringDatabaseOperations.getCreateProfessionalRequest(userId);
        smuHiringDatabaseOperations.removeProfessionalCreateRequest(userId);
        String content = "We regret to inform that the professional qualifications does not meet our requirement for \n" +
                "username: " + professionalRequest.getUserId() + "\n.";
        smuHiringDatabaseOperations.sendEmail(professionalRequest.getEmail(), "Professional Registration Denied", content);
    }

    public List<Professional> getDeleteProfessionalRequests() {
        return smuHiringDatabaseOperations.getDeleteProfessionalRequests();
    }

    public List<Employer> getDeleteEmployerRequests() {
        return smuHiringDatabaseOperations.getDeleteEmployerRequests();
    }

    public void requestJobMatching(String userId) {
        smuHiringDatabaseOperations.requestJobMatching(userId);
    }
}
