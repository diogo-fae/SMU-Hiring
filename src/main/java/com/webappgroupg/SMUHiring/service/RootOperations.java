package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;
import com.webappgroupg.SMUHiring.model.Staff;

public class RootOperations {
    private DatabaseOperations dbOps = new DatabaseOperations();

    // Constructor
    public RootOperations() {
    }

    public void createStaff(String userId, String firstName, String lastName, String email, int phoneNumber) {
        Staff staff = new Staff(userId, firstName, lastName, email, phoneNumber, "active");
        dbOps.createUserAccount(staff);
    }

    // TODO: Delete, used for testing
    public static void main(String[] args) {
        RootOperations rootOps = new RootOperations();
        rootOps.createStaff("staffX", "John", "Doe", "john.doe@gmail.com", 1234567890);
    }
}
