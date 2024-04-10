package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;
import com.webappgroupg.SMUHiring.model.Staff;
import com.webappgroupg.SMUHiring.model.User;

import java.util.ArrayList;

public class RootOperations {
    private DatabaseOperations dbOps = new DatabaseOperations();

    // Constructor
    public RootOperations() {
    }

    public void createStaff(String userId, String firstName, String lastName, String email, int phoneNumber) {
        Staff staff = new Staff(userId, firstName, lastName, email, phoneNumber, "active");
        dbOps.createUserAccount(staff);
    }

    public void printUserIds(){
        ArrayList<User> users = dbOps.getAllUsers();
        System.out.println("User IDs:");
        for (User user : users) {
            System.out.print(user.getUserId() + " ");
        }
        System.out.println("\n--------------------");
    }

    // TODO: Delete, used for testing
    public static void main(String[] args) {
        RootOperations rootOps = new RootOperations();
        rootOps.createStaff("staffX", "John", "Doe", "john.doe@gmail.com", 1234567890);
    }
}
