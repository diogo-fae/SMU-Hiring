package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.SmuHiringDatabaseOperations;
import com.webappgroupg.SMUHiring.model.Employer;
import com.webappgroupg.SMUHiring.model.JobPosting;
import com.webappgroupg.SMUHiring.model.Payment;

import java.util.ArrayList;

public class EmployerOperations {
   /* private SmuHiringDatabaseOperations dbOps = new SmuHiringDatabaseOperations();
    private Employer employerUser;

    // Constructors
    public EmployerOperations(String userId) {
        Employer tempEmployer = dbOps.getEmployerDetails(userId);
        this.employerUser = new Employer(tempEmployer);
    }
    public EmployerOperations(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String company) {
        this.employerUser = new Employer(userId, firstName, lastName, email, phoneNumber, status, address1, address2, city, state, zipCode, company);
    }
    public EmployerOperations(String userId, String firstName, String lastName, String email, long phoneNumber, String status, String address1, String city, String state, int zipCode, String company) {
        this.employerUser = new Employer(userId, firstName, lastName, email, phoneNumber, status, address1, city, state, zipCode, company);
    }
    public void closeConnection(){
        dbOps.closeConnection();
    }

    // Request for new account
    public void requestNewAccount() {
        dbOps.requestNewEmployerAccount(employerUser);
    }
    public void requestNewAccount(String firstName, String lastName, String email, long phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String company) {
        employerUser.setFirstName(firstName);
        employerUser.setLastName(lastName);
        employerUser.setEmail(email);
        employerUser.setPhoneNumber(phoneNumber);
        employerUser.setStatus(status);
        employerUser.setAddress1(address1);
        employerUser.setAddress2(address2);
        employerUser.setCity(city);
        employerUser.setState(state);
        employerUser.setZipCode(zipCode);
        employerUser.setCompany(company);
        dbOps.requestNewEmployerAccount(employerUser);
    }
    // Request account deletion
    public void requestAccountDeletion() {
        dbOps.requestAccountDeletion(employerUser.getUserId());
    }

    // Payment
    public void makePayment(String paymentId, Double paymentAmount, String paymentDate, String dueDate) {
        Payment payment = new Payment(employerUser.getUserId(), paymentId, paymentAmount, paymentDate, dueDate);
        dbOps.makePayment(payment);
    }
    public ArrayList<Payment> seePayments(){
        return dbOps.getPayments(employerUser.getUserId());
    }

    // Job Postings
    public void createJobPosting(int jobId, String positionName, String supervisorName, String supervisorEmail, String startDate, String endDate, String startTime, String endTime, double payPerHour) {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        dbOps.createJobPosting(jobId, employerUser.getCompany(), positionName, supervisorName, supervisorEmail, startDate, endDate, startTime, endTime, payPerHour);
    }
    public void deleteJobPosting(int jobId) {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        dbOps.deleteJobPosting(jobId, employerUser.getCompany());
    }
    public ArrayList<JobPosting> getJobPostings() {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        return dbOps.getJobPostings(employerUser.getCompany());
    }
    public void updateJobPosting(int jobId, String positionName, String supervisorName, String supervisorEmail, String startDate, String endDate, String startTime, String endTime, double payPerHour) {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        dbOps.updateJobPosting(jobId, employerUser.getCompany(), positionName, supervisorName, supervisorEmail, startDate, endDate, startTime, endTime, payPerHour);
    }
    public void addJobQualification(int jobId, String category, String keyword) {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        dbOps.createJobQualification(jobId, employerUser.getCompany(), category, keyword);
    }
    public void deleteJobQualification(int jobId, String category, String keyword) {
        employerUser.setCompany(dbOps.getEmployerCompany(employerUser.getUserId()));
        dbOps.deleteJobQualification(jobId, employerUser.getCompany(), category, keyword);
    }

    // Edit account
    public void editAccount(String firstName, String lastName, String email, long phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String company) {
        this.employerUser.setFirstName(firstName);
        this.employerUser.setLastName(lastName);
        this.employerUser.setEmail(email);
        this.employerUser.setPhoneNumber(phoneNumber);
        this.employerUser.setStatus(status);
        this.employerUser.setAddress1(address1);
        this.employerUser.setAddress2(address2);
        this.employerUser.setCity(city);
        this.employerUser.setState(state);
        this.employerUser.setZipCode(zipCode);
        this.employerUser.setCompany(company);
        dbOps.updateEmployerAccount(this.employerUser);
    }
    public void changePassword(String newPassword) {
        dbOps.changePassword(employerUser.getUserId(), newPassword);
    }
    public String getPassword(){
        return dbOps.getPassword(employerUser.getUserId());
    }

    public Employer getEmployerUser() {
        return employerUser;
    }

    @Override
    public String toString(){
        // Print id, address1, address2, and zipCode
        return "Employer{" + "userId='" + employerUser.getUserId() + "' address1=" + employerUser.getAddress1() + " address2=" + employerUser.getAddress2() + " zipCode=" + employerUser.getZipCode() + "}";
    }

    public static void main(String[] args) {
//        EmployerOperations employerOps = new EmployerOperations("test", "test", "test", "test", 1234567890, "test", "test", "test", "TS", 12345, "test");
        EmployerOperations employerOps = new EmployerOperations("test");
//        employerOps.requestNewAccount();
//        employerOps.requestAccountDeletion();
//        employerOps.makePayment("test", 100.0, "2022-03-14", "2022-03-15");
//        employerOps.createJobPosting(1, "test", "test", "test", "2022-03-14", "2022-03-15", "12:00:00", "13:00:00", 100.0);
//        employerOps.updateJobPosting(1, "testUpdate", "test", "test", "2022-03-14", "2022-03-15", "18:00:00", "19:00:00", 20.0);
//        employerOps.deleteJobPosting(1);
//        employerOps.addJobQualification(1, "test", "test");
//        employerOps.addJobQualification(1, "test", "test2");
//        employerOps.addJobQualification(1, "test2", "test");
//        employerOps.deleteJobQualification(1, "test", "test");
//        System.out.println(employerOps.getJobPostings());
//        employerOps.editAccount("updatedTest", "updatedTest", "updatedTest", 1234567890, "updatedTest", "updatedTest", "updatedTest", "TS", "TS", 12345, "updatedTest");
//        employerOps.changePassword("testChanged");
        employerOps.closeConnection();
    }*/
}
