package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;
import com.webappgroupg.SMUHiring.model.Staff;

public class RootOperations {
    private DatabaseOperations dbOps = new DatabaseOperations();

    public void createStaff(String userId, String firstName, String lastName, String email, int phoneNumber) {
        Staff newStaff = new Staff(userId, firstName, lastName, email, phoneNumber);

        dbOps.createUserAccount(newStaff.getUserId(), newStaff.getFirstName(), newStaff.getLastName(), newStaff.getEmail(), newStaff.getPhoneNumber(), "S");
    }

    // TODO: Delete, used for testing
//    public static void main(String[] args) {
//        RootOperations rootOps = new RootOperations();
//        rootOps.createStaff("1234", "John", "Doe", "john.doe@gmail.com", 1234567890);
//    }
}
