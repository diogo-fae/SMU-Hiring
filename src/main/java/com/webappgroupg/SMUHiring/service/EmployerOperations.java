package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;
import com.webappgroupg.SMUHiring.model.Employer;

public class EmployerOperations {
    private DatabaseOperations dbOps = new DatabaseOperations();
    private Employer employerUser;

    // Constructors
    public EmployerOperations(String userId) {
        this.employerUser = new Employer(userId);
    }
    public EmployerOperations(String userId, String firstName, String lastName, String email, int phoneNumber, String status, String address1, String address2, String city, String state, int zipCode, String company) {
        this.employerUser = new Employer(userId, firstName, lastName, email, phoneNumber, status, address1, address2, city, state, zipCode, company);
    }
    public EmployerOperations(String userId, String firstName, String lastName, String email, int phoneNumber, String status, String address1, String city, String state, int zipCode, String company) {
        this.employerUser = new Employer(userId, firstName, lastName, email, phoneNumber, status, address1, city, state, zipCode, company);
    }

    // Request for new account
    public void requestNewAccount() {
        dbOps.requestNewEmployerAccount(employerUser);
    }
    // Request account deletion
    public void requestAccountDeletion() {
        dbOps.requestAccountDeletion(employerUser.getUserId());
    }

    public static void main(String[] args) {
        EmployerOperations employerOps = new EmployerOperations("test", "test", "test", "test", 1234567890, "test", "test", "test", "TS", 12345, "test");
//        employerOps.requestNewAccount();
//        employerOps.requestAccountDeletion();
    }
}
